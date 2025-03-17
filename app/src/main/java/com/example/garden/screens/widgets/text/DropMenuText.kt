package com.example.garden.screens.widgets.text

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.garden.ui.theme.FontBlackColor

@Composable
fun DropMenuText(text: String, onClick: () -> Unit) {
    Text(
        modifier = Modifier.clickable {
            onClick()
        },
        text = text,
        fontSize = 18.sp,
        color = FontBlackColor
    )
}