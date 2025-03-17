package com.example.garden.screens.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.text.isDigitsOnly
import com.example.garden.R
import com.example.garden.data.DataSource
import com.example.garden.screens.widgets.text.AlertConfirmText
import com.example.garden.screens.widgets.text.AlertDataTextField
import com.example.garden.screens.widgets.text.AlertDismissText
import com.example.garden.screens.widgets.text.AlertListReasonTextField
import com.example.garden.screens.widgets.text.AlertTextField
import com.example.garden.screens.widgets.text.AlertTitle
import com.example.garden.ui.theme.White
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangesAlertDialog(
    onConfirm: (
        date: Date,
        type: Int,
        reason: String,
        amount: String
    ) -> Unit,
    onDismiss: () -> Unit
) {
    val datePicker = rememberDatePickerState()
    var date by remember {
        mutableStateOf("")
    }
    var type_reason by remember {
        mutableStateOf(R.string.none)
    }

    var reason by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }

    date = datePicker.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""
    var toast_text = stringResource(R.string.check_data)
    val context = LocalContext.current
    AlertDialog(
        containerColor = White,
        title = {
            AlertTitle(text = stringResource(R.string.change_amount_bed))
        },
        text = {
            Column() {
                AlertDataTextField(
                    value = date,
                    label = stringResource(R.string.date) + ":",
                    datePickerState = datePicker
                )
                AlertListReasonTextField(
                    value = stringResource(type_reason),
                    list = DataSource().getReasons(),
                    onChange = { type_reason = it },
                    label = stringResource(R.string.type_reason) + ":"
                )

                AlertTextField(
                    value = reason,
                    onChange = { reason = it },
                    label = stringResource(R.string.reason)
                )
                AlertTextField(
                    value = amount,
                    onChange = { amount = it },
                    label = stringResource(R.string.amount),
                    isNumber = true
                )

            }
        },
        onDismissRequest = {
            //onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (reason.isNotEmpty() && amount.isNotEmpty() && amount.isDigitsOnly()
                        && date.isNotEmpty() && type_reason != R.string.none

                    ) {
                        onConfirm(
                            datePicker.selectedDateMillis?.let { Date(it) } ?: Date(0),
                            type_reason,
                            reason,
                            amount
                        )
                        onDismiss()
                    } else {
                        Toast.makeText(context, toast_text, Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                AlertConfirmText(
                    text = stringResource(R.string.alert_save)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                AlertDismissText(
                    text = stringResource(R.string.alert_dismiss)
                )
            }
        }
    )
}

//@Preview
//@Composable
//private fun AlertChanges() {
//    ChangesAlertDialog(
//        {},
//        {}
//    )
//}