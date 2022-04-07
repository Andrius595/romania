package edu.ktu.romania.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import edu.ktu.romania.ui.theme.mainBrightWhite
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable component for customizable monimoto default button
 */
@Composable
fun Button(
    onClick: () -> Unit,
    buttonText: String,
    buttonColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    borderColor: Color,
    enabled: Boolean = true
) {
    Button(
        onClick = {
            if (enabled)
                onClick()
            else
                Unit
        },
        elevation = null,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        border = BorderStroke(2.dp, Color.Black),
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        if (enabled) {
            Text(
                text = buttonText.uppercase(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        } else {
            CircularProgressIndicator(color = mainBrightWhite, modifier = Modifier.size(24.dp))
        }

    }
}