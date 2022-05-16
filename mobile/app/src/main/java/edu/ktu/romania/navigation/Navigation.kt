package edu.ktu.romania.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ktu.romania.navigation.screens.*
import edu.ktu.romania.ui.theme.mainBlue
import edu.ktu.romania.viewmodels.MainViewModel

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel){

    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Registration.route){
            Registration(navController = navController, mainViewModel = viewModel)
        }
        composable(Screen.Login.route){
            Login(navController, mainViewModel = viewModel)
        }
        composable(Screen.Home.route){
            Home(mainViewModel = viewModel)
        }
        composable(Screen.Modules.route){
            Modules(mainViewModel = viewModel)
        }
        composable(Screen.Chat.route){
            Chat(mainViewModel = viewModel)
        }
    }
}
