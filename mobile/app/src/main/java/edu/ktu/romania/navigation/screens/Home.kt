package edu.ktu.romania.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import edu.ktu.romania.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.ktu.romania.ui.theme.mainBrightWhite
import edu.ktu.romania.viewmodels.MainViewModel

@Composable
fun Home(mainViewModel: MainViewModel) {
    val isLoggedIn = mainViewModel.isLoggedIn
    if (isLoggedIn) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ktu_logo_en_full),
                "",
                modifier = Modifier.size(300.dp)
            )

        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Please log in",
                fontSize = 32.sp
            )
        }
    }
}