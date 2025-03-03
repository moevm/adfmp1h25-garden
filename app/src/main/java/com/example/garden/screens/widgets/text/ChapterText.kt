package com.example.garden.screens.widgets.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.garden.ui.theme.FontBlackColor

@Composable
fun ChapterText(text:String) {
    Text(
        text = text,
        color = FontBlackColor,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    )
}