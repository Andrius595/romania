package edu.ktu.romania.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ktu.romania.navigation.screens.Home
import edu.ktu.romania.navigation.screens.Login
import edu.ktu.romania.navigation.screens.Registration

@Composable
fun Navigation(navController: NavHostController){

    NavHost(navController = navController, startDestination = Screen.Login.route){
        composable(Screen.Registration.route){
            Registration(navController = navController)
        }
        composable(Screen.Login.route){
            Login(navController)
        }
        composable(Screen.Home.route){
            Home(navController)
        }
    }
}
