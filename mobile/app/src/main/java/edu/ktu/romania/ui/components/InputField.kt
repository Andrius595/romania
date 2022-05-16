package edu.ktu.romania.ui.components

import edu.ktu.romania.ui.theme.*

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Composable to showcase input field
 * in TextField form
 */
@Composable
fun InputField(
    placeholder: String,
    onValueChange: (String) -> Unit,
    value: String, modifier: Modifier,
    wasValidated: Boolean,
    errorMessage: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation =  VisualTransformation.None,
    ) {
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = mainBrightWhite,
            ),
            placeholder = {
                Text(
                    placeholder,
                    color = mainLightGrey) },
            trailingIcon = {
                if (!wasValidated) Icon(
                    Icons.Filled.Info,
                    tint = alertColor,
                    contentDescription = ""
                )
            },
            shape = RectangleShape,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation
        )
        if (!wasValidated) {
            Text(errorMessage, color = alertColor)
        }
    }
}