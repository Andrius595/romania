package edu.ktu.romania.navigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.ktu.romania.api.model.User
import edu.ktu.romania.navigation.Screen
import edu.ktu.romania.ui.components.Button
import edu.ktu.romania.ui.components.Card
import edu.ktu.romania.ui.components.InputField
import edu.ktu.romania.ui.theme.borderColor
import edu.ktu.romania.ui.theme.mainBrightWhite
import edu.ktu.romania.viewmodels.MainViewModel

const val name = "romania@ktu.lt"
const val password = "ktu123"

@Composable
fun Login(navController: NavController, mainViewModel: MainViewModel) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoggedIn by remember { mutableStateOf(mainViewModel.isLoggedIn) }
    var wasValidated = true
    var errorMessage by remember { mutableStateOf("") }

    if (!isLoggedIn) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Card {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text("Login", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Username:", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(24.dp))
                        InputField(
                            placeholder = "Mark",
                            onValueChange = { username = it },
                            value = username,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Black)
                                .height(54.dp),
                            wasValidated = wasValidated,
                            errorMessage = errorMessage
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Password: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(22.dp))
                        InputField(
                            placeholder = "",
                            onValueChange = { password = it },
                            value = password,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Black)
                                .height(54.dp),
                            wasValidated = wasValidated,
                            errorMessage = errorMessage,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = PasswordVisualTransformation(),
                        )

                    }
                    Spacer(modifier = Modifier.height(36.dp))
                    Button(
                        onClick = {
                            mainViewModel.logInUser(
                                User(name = username, password = password),
                                logIn = { navController.navigate(Screen.Home.route) },
                                callback = {
                                    wasValidated = false; errorMessage = "Invalid credentials"
                                }
                            )


                        },
                        buttonText = "Login",
                        buttonColor = mainBrightWhite,
                        textColor = Color.Black.copy(0.4F),
                        borderColor = Color.Black.copy(0.4F)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Forgot your password?")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { navController.navigate(Screen.Registration.route) },
                        buttonText = "Registration",
                        buttonColor = mainBrightWhite,
                        textColor = Color.Black.copy(0.4F),
                        borderColor = Color.Black.copy(0.4F)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { mainViewModel.logOutUser(); navController.navigate(Screen.Home.route) },
                buttonText = "Log out",
                buttonColor = Color.Red,
                textColor = mainBrightWhite,
                borderColor = mainBrightWhite,
                modifier = Modifier.padding(horizontal = 64.dp)
            )
        }
    }
}


