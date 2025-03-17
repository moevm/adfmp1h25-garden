package com.example.garden.screens.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.garden.R
import com.example.garden.screens.widgets.text.AlertConfirmText
import com.example.garden.screens.widgets.text.AlertContentText
import com.example.garden.screens.widgets.text.AlertDismissText
import com.example.garden.screens.widgets.text.AlertTitle
import com.example.garden.ui.theme.White

@Composable
fun ArchiveAlertDialog(
    onDismiss: () -> Unit, onConfirm: () -> Unit, bed_title: String
) {
    AlertDialog(
        containerColor = White,
        title = {
            AlertTitle(stringResource(R.string.restore_title))
        },
        text = {
            AlertContentText(stringResource(R.string.restore_mes) + " " + bed_title + " ?")
        },
        onDismissRequest = {
            //onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                AlertConfirmText(
                    text = stringResource(R.string.restore)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                AlertDismissText(
                    text = stringResource(R.string.alert_dismiss)
                )
            }
        }
    )
}