package edu.ktu.romania.navigation.screens

import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import edu.ktu.romania.api.model.Message
import edu.ktu.romania.api.model.Module
import edu.ktu.romania.ui.components.InputField
import edu.ktu.romania.ui.theme.mainBrightWhite
import edu.ktu.romania.viewmodels.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Chat(mainViewModel: MainViewModel) {
    val messages = mainViewModel.chatMessages
    var addMessage by remember { mutableStateOf(false) }
    var showEdit by remember { mutableStateOf(false) }
    var currentMessage by remember { mutableStateOf<Message?>(null) }


    var isLoggedIn = mainViewModel.isLoggedIn
    if (isLoggedIn) {
        if (messages.isNotEmpty()) {
            LazyColumn {
                stickyHeader {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            "Messages".uppercase(),
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
                                addMessage = true
                            }
                        )
                    }
                }
                items(messages) {
                    chatItem(
                        msg = it,
                        onClick = {
                            currentMessage = it;
                            showEdit = true
                        }
                    )
                }
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
    if (addMessage) {
        AddMessage(
            onDismiss = { addMessage = false },
            mainViewModel = mainViewModel
        )
    }
    if(showEdit){
        EditComment(msg = currentMessage!!, mainViewModel = mainViewModel) {
            showEdit = false
        }
    }

}


@Composable
fun chatItem(msg: Message, onClick: (Message) -> Unit) {
    Row(
        modifier = Modifier
            .background(mainBrightWhite)
            .border(1.dp, Color.Black)
            .clickable {
                onClick(msg)
            }
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(imageVector = Icons.Default.Email, contentDescription = "")
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            msg.content, color = Color.Black,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            msg.name, color = Color.Black,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

    }
}
@Composable
fun EditComment(msg: Message, mainViewModel: MainViewModel, onDismiss: () -> Unit) {
    var messageAuthor by remember {mutableStateOf(msg.name)}
    var messageContent by remember {mutableStateOf(msg.content)}

    Dialog(onDismissRequest = { onDismiss() }) {
        edu.ktu.romania.ui.components.Card() {
            Column(modifier = Modifier.padding(24.dp)) {
                InputField(
                    placeholder = "",
                    onValueChange = {messageAuthor = it},
                    value = messageAuthor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .height(54.dp),
                    wasValidated = true,
                    errorMessage = "",
                )
                Spacer(modifier = Modifier.height(16.dp))
                InputField(
                    placeholder = "",
                    onValueChange = {messageContent = it},
                    value = messageContent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .height(54.dp),
                    wasValidated = true,
                    errorMessage = "",

                )
                Spacer(modifier = Modifier.height(16.dp))
                edu.ktu.romania.ui.components.Button(
                    onClick = {
                        mainViewModel.editComment(msg,
                            Message(name = messageAuthor,
                            content = messageContent))
                        onDismiss()
                    },
                    buttonText = "Edit comment",
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
fun AddMessage(onDismiss: () -> Unit, mainViewModel: MainViewModel) {
    var message by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        edu.ktu.romania.ui.components.Card() {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Leave a comment")
                TextField(
                    value = message,
                    placeholder = { Text(text = "Comment") },
                    onValueChange = { message = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = name,
                    placeholder = { Text(text = "Name") },
                    onValueChange = { name = it }
                )
                Button(

                    onClick = {
                        onDismiss();
                        mainViewModel.AddComment(name = name, message = message)
                    }) {
                    Text("Send")
                }

            }
        }
    }
}