package com.example.garden.screens.widgets.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.garden.ui.theme.FontBlackColor

@Composable
fun BigCardText(
    text: String
) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = FontBlackColor,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun SmallCardText(
    text: String
) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = FontBlackColor,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun MediumCardText(
    text: String
) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = FontBlackColor,
        fontWeight = FontWeight.Medium
    )
}