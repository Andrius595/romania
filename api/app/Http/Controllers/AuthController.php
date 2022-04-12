<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Hash;
use App\Models\User;

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
}
