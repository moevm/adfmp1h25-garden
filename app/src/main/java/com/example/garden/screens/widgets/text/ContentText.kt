package com.example.garden.screens.widgets.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.garden.ui.theme.FontBlackColor

@Composable
fun ContentText(text:String) {
    Text(
        text = text,
        color = FontBlackColor,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    )
}