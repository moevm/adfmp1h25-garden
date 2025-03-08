package com.example.garden.screens.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.garden.R
import com.example.garden.screens.widgets.text.AlertConfirmText
import com.example.garden.screens.widgets.text.AlertDismissText
import com.example.garden.screens.widgets.text.AlertTitle
import com.example.garden.ui.theme.White

@Composable
fun WarningAlertDialog(title:String, onDismiss:()->Unit, onConfirm:()->Unit) {
    AlertDialog(
        containerColor = White,
        title = {
            AlertTitle(title)
        },
        onDismissRequest = {
            //onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                AlertConfirmText(
                    text = stringResource(R.string.yes)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                AlertDismissText(
                    text = stringResource(R.string.no)
                )
            }
        }
    )
}