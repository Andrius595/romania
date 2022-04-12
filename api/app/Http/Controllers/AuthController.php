<?php

namespace App\Http\Controllers;

use App\Http\Requests\ResetPasswordRequest;
use App\Models\User;
use Illuminate\Auth\Events\PasswordReset;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Password;
use Illuminate\Support\Str;

class AuthController extends Controller
{
    public function register(Request $request){
        $fields = $request->validate([
            'name' => 'required|string',
            'email' => 'required|string|unique:users,email',
            'password' => 'required|string|confirmed|min:8'
        ]);

        $user = User::create([
            'name' => $fields['name'],
            'email' => $fields['email'],
            'password' => bcrypt($fields['password'])
        ]);

        $response = [
            'user' => $user,
        ];
        return response()->json($response);
    }
    public function login(Request $request){
         $fields = $request->validate([
             'email' => 'required|string',
             'password' => 'required|string'
         ]);

         //check email
         $user = User::where('email', $fields['email'])->first();

         //check password

         if(!$user || !Hash::check($fields['password'], $user->password)){
             return response([
                 'message' => 'Bad Credentials'
             ], 401);
         }

         $token = $user->createToken('myapptoken')->plainTextToken;

         $response = [
             'user' => $user,
             'token' => $token
         ];
        return response()->json($response);
    }

    public function logout(Request $request){
        auth()->user()->tokens()->delete();
        return response([
            'message' => 'Logged out'
        ], 360);
        return response()->json(['Logged out']);
    }

    public function forgotPassword(Request $request): JsonResponse
    {
        $request->validate(['email' => 'required|email']);

        $status = Password::sendResetLink($request->only('email'));

        if ($status === Password::RESET_LINK_SENT) {
            return response()->json(['success' => true]);
        }

        return response()->json(['errors' => ['password' => ['Something went wrong']]], Response::HTTP_BAD_REQUEST);
    }

    public function resetPassword(ResetPasswordRequest $request): JsonResponse
    {
        $status = Password::reset($request->only('email', 'token', 'password', 'password_confirmation'),
            function ($user, $password) {
                $user->forceFill([
                    'password' => bcrypt($password)
                ])->setRememberToken(Str::random(60));

                $user->save();

                event(new PasswordReset($user));
            });

        if ($status === Password::PASSWORD_RESET) {
            return response()->json(['success' => true]);
        }

        return response()->json(['errors' => ['reset' => ['Unable to reset password']]], Response::HTTP_BAD_REQUEST);
    }
}
