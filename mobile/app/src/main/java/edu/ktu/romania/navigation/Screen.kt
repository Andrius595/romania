package edu.ktu.romania.navigation

sealed class Screen(val route: String){
    object Login: Screen("login_screen")
    object Registration: Screen("registration_screen")
    object Home: Screen("home_screen")
    object Modules: Screen("modules_screen")
    object Chat: Screen("chat_screen")

    fun withArgs(vararg args: String): String{
        return buildString{
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
