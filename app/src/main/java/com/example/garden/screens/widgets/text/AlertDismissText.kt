package com.example.garden.screens.widgets.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.Red

@Composable
fun AlertDismissText(text:String) {
    Text(
        text = text.uppercase(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Red
    )
}