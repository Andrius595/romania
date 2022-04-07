package edu.ktu.romania.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.ktu.romania.ui.theme.mainBrightWhite

@Composable
fun Card(content: @Composable () -> Unit){
    Box(
        modifier = Modifier
            .background(mainBrightWhite)
            .border(1.dp, Color.Black.copy(alpha = 0.4F))
    )
    {
        content()
    }
}