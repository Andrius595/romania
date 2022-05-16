package edu.ktu.romania

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.ktu.romania.data.NavItem
import edu.ktu.romania.navigation.Navigation
import edu.ktu.romania.navigation.Screen
import edu.ktu.romania.ui.components.BottomNavigationBar
import edu.ktu.romania.ui.theme.RomaniaTheme
import edu.ktu.romania.ui.theme.appBackgroundColor
import edu.ktu.romania.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    lateinit private var navController: NavHostController
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        WindowCompat.setDecorFitsSystemWindows(window, true) // True for button to move when opening keyboard

        setContent {
            navController = rememberNavController()

            RomaniaTheme {
                Scaffold(

                    bottomBar = {
                        BottomNavigationBar(
                            menu = listOf(
                                NavItem(
                                    name = "Home",
                                    route = Screen.Home.route,
                                    icon = Icons.Default.Home
                                ),
                                NavItem(
                                    name = "Modules",
                                    route = Screen.Modules.route,
                                    icon = Icons.Default.Star
                                ),
                                NavItem(
                                    name = "Chat",
                                    route = Screen.Chat.route,
                                    icon = Icons.Default.Email
                                ),
                                NavItem(
                                    name = "Login",
                                    route = Screen.Login.route,
                                    icon = Icons.Default.Face
                                ),
                            ),
                            navController = navController,
                            onClick ={
                                navController.navigate(it.route)
                            }

                        )
                    }
                        ) {
                        Navigation(navController = navController, viewModel)
                    }
                    }
            }
        }
    }


@Composable
fun Greeting(name: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RomaniaTheme {
        Greeting("Android")
    }
}