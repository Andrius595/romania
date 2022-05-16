package edu.ktu.romania.navigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import edu.ktu.romania.api.model.Module
import edu.ktu.romania.api.model.User
import edu.ktu.romania.ui.theme.appBackgroundColor
import edu.ktu.romania.ui.theme.mainBrightWhite
import edu.ktu.romania.viewmodels.MainViewModel

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Modules(mainViewModel: MainViewModel) {
    var showInfo by remember { mutableStateOf(false) }
    var currentModule by remember { mutableStateOf<Module?>(null) }
    var modules by remember { mutableStateOf(mainViewModel.modules) }
    var addModule by remember { mutableStateOf(false) }
    var isLoggedIn = mainViewModel.isLoggedIn

    if (isLoggedIn) {
        LazyColumn(modifier = Modifier.padding(bottom = 32.dp)) {
            stickyHeader {
                Row(
                    modifier = Modifier.padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        "Modules".uppercase(),
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Add,
                        "",
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false)
                        ) {
                            addModule = true
                        }
                    )
                }
            }
            items(modules) { it ->
                ListItem(
                    module = it,
                    onClick = {
                        currentModule = it
                        showInfo = true
                    },
                )
            }
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
    if (showInfo) {
        ModuleInfo(currentModule!!, mainViewModel = mainViewModel) {
            showInfo = false
        }
    }
    if (addModule) {
        AddModule(
            onDismiss = { addModule = false },
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun ListItem(module: Module, onClick: (Module) -> Unit) {
    Row(modifier = Modifier
        .border(1.dp, Color.Black)
        .clickable { onClick(module) }
        .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(imageVector = Icons.Rounded.Home, contentDescription = "")
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            module.name, color = Color.Black,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

    }
}

@Composable
fun ModuleInfo(module: Module,mainViewModel: MainViewModel, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        edu.ktu.romania.ui.components.Card() {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    "Module ID: " + module.id,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Module name: " + module.name,
                    fontSize = 24.sp

                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Credits: " + module.credits,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                edu.ktu.romania.ui.components.Button(
                    onClick = {
                        mainViewModel.deleteModule(module)
                        onDismiss()
                    },
                    buttonText = "Remove module",
                    buttonColor = Color.Red,
                    textColor = mainBrightWhite,
                    borderColor = mainBrightWhite,
                    modifier = Modifier.align(Alignment.End)
                )

            }
        }
    }
}

@Composable
fun AddModule(onDismiss: () -> Unit, mainViewModel: MainViewModel) {
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var credits by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        edu.ktu.romania.ui.components.Card() {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Add a module")
                TextField(
                    value = name,
                    placeholder = { Text(text = "Name") },
                    onValueChange = { name = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = credits,
                    placeholder = { Text(text = "Credits") },
                    onValueChange = { credits = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = id,
                    placeholder = { Text(text = "Module ID") },
                    onValueChange = { id = it }
                )
                Button(

                    onClick = {
                        onDismiss();
                        mainViewModel.AddModule(name = name, id = id, credits = credits)
                    }) {
                    Text("Confirm")
                }

            }
        }
    }
}