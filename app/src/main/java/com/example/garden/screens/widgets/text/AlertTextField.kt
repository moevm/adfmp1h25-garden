package com.example.garden.screens.widgets.text

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.FontBlackColor
import com.example.garden.ui.theme.FontGrayColor
import com.example.garden.ui.theme.White

@Composable
fun AlertTextField(value:String,
                   onChange:(String)->Unit,
                   label:String,
                   isNumber: Boolean = false,
                   imeAction: ImeAction = ImeAction.Next,
) {
    val focusManager = LocalFocusManager.current
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
        label = {Text(text = label)},
         keyboardOptions = if(isNumber)
             KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = imeAction)
         else KeyboardOptions(
                 imeAction = imeAction
             ),
        keyboardActions = if(imeAction == ImeAction.Done)
            KeyboardActions(
                 onDone = { focusManager.clearFocus()}
        )else KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down)}
        )
    )
}