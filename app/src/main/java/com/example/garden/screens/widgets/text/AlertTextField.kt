package com.example.garden.screens.widgets.text

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.garden.ui.theme.FontGrayColor
import com.example.garden.ui.theme.White

@Composable
fun AlertTextField(value:String, onChange:(String)->Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedTextColor = FontGrayColor,
            unfocusedTextColor = FontGrayColor
        )
    )
}