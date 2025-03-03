package com.example.garden.screens.widgets.text

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.FontBlackColor
import com.example.garden.ui.theme.FontGrayColor
import com.example.garden.ui.theme.White

@Composable
fun AlertTextField(value:String, onChange:(String)->Unit, label:String) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            unfocusedLabelColor = FontGrayColor,
            focusedLabelColor = FontBlackColor,
            unfocusedIndicatorColor = DarkGreen,
            focusedIndicatorColor = DarkGreen
        ),
        maxLines = 3,
        label = {Text(text = label)}
    )
}