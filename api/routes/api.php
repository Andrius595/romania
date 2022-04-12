<?php

use App\Http\Controllers\AuthController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

//Public routes
// TODO remove this when our own password reset mail will be created.
Route::get('reset-password/{token}', function(){})->name("password.reset");

Route::post('/register', [AuthController::class, 'register']);
Route::post('/login', [AuthController::class, 'login']);
Route::get('forgot-password', [AuthController::class, 'forgotPassword'])->name('password.request');
Route::post('/reset-password', [AuthController::class, 'resetPassword'])->name('password.update');



//Protected routes

Route::group(['middleware' => ['auth:sanctum']], function () {
Route::post('/logout', [AuthController::class, 'logout']);
});
