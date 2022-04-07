package edu.ktu.romania.navigation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*

import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.ktu.romania.navigation.Screen
import edu.ktu.romania.ui.components.Button
import edu.ktu.romania.ui.components.Card
import edu.ktu.romania.ui.components.InputField
import edu.ktu.romania.ui.theme.borderColor
import edu.ktu.romania.ui.theme.mainBrightWhite
import edu.ktu.romania.R


@Composable
fun Registration(navController: NavController) {
    var username by remember {mutableStateOf("")}

    Icon(
        painter = painterResource(id = R.drawable.ic_back),
        "",
        modifier = Modifier.padding(start = 8.dp, top = 8.dp).clickable { navController.popBackStack() })
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
                Text("Register", fontSize = 32.sp, fontWeight = FontWeight.Bold)
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
                        onValueChange = {username = it},
                        value = username,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .height(54.dp),
                        wasValidated = true,
                        errorMessage = ""
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Password: ", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(24.dp))
                    InputField(
                        placeholder = "",
                        onValueChange = {},
                        value = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .height(54.dp),
                        wasValidated = true,
                        errorMessage = ""
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Password: ",fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(24.dp))
                    InputField(
                        placeholder = "",
                        onValueChange = {},
                        value = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .height(54.dp),
                        wasValidated = true,
                        errorMessage = ""
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                Button(
                    onClick = { /*TODO*/ },
                    buttonText = "Create account",
                    buttonColor = mainBrightWhite,
                    textColor = Color.Black.copy(0.4F),
                    borderColor = Color.Black.copy(0.4F)
                )
                Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }
}