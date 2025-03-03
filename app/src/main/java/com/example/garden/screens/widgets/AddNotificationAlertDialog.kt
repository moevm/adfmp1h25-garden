package com.example.garden.screens.widgets

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.screens.widgets.text.AlertConfirmText
import com.example.garden.screens.widgets.text.AlertContentText
import com.example.garden.screens.widgets.text.AlertDataTextField
import com.example.garden.screens.widgets.text.AlertDismissText
import com.example.garden.screens.widgets.text.AlertTextField
import com.example.garden.screens.widgets.text.AlertTitle
import com.example.garden.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotificationAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (
        title: String,
        bed_id: String,
        description: String,
        dateStart: Date,
        dateEnd: Date,
    ) -> Unit,
    listBeds: List<Bed>
) {

    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    var dateStart by remember {
        mutableStateOf("")
    }
    var dateEnd by remember {
        mutableStateOf("")
    }
    var bed by remember {
        mutableStateOf(
            Bed(
                title = "",
                description = "",
                sort = "",
                amount = 0,
                date_sowing = Date(0)
            ),
        )
    }
    val datePickerStateStart = rememberDatePickerState()
    dateStart = datePickerStateStart.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    val datePickerStateEnd = rememberDatePickerState()
    dateEnd = datePickerStateEnd.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    AlertDialog(
        containerColor = White,
        title = {
            AlertTitle(text = stringResource(R.string.alert_add_event_title))
        },
        text = {
            Column() {

                AlertTextField(
                    value = name,
                    onChange = { name = it },
                    label = stringResource(R.string.alert_add_event_name) + ":"
                )


                //AlertContentText()
                AlertDataTextField(
                    value = dateStart,
                    label = stringResource(R.string.alert_add_event_start) + ":",
                    datePickerState = datePickerStateStart
                )
                AlertDataTextField(
                    value = dateEnd,
                    label = stringResource(R.string.alert_add_event_end) + ":",
                    datePickerState = datePickerStateEnd
                )

                AlertTextField(
                    value = description,
                    onChange = { description = it },
                    label = stringResource(R.string.alert_add_event_description) + ":"
                )


                AlertTextField(
                    value = bed.title,
                    listBeds = listBeds,
                    label = stringResource(R.string.alert_add_event_bed) + ":",
                    onChange = {
                        bed = it
                    }
                )

            }
        },
        onDismissRequest = {
            //onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (name.isNotEmpty() && description.isNotEmpty() && bed.title.isNotEmpty()
                        && dateStart.isNotEmpty() && dateEnd.isNotEmpty() &&
                        datePickerStateStart.selectedDateMillis!! <= datePickerStateEnd.selectedDateMillis!!
                    ){
                        onConfirmation(
                            name,
                            bed.id.toString(),
                            description,
                            datePickerStateStart.selectedDateMillis?.let { Date(it) } ?: Date(0),
                            datePickerStateEnd.selectedDateMillis?.let { Date(it) } ?: Date(0),
                        )
                        onDismissRequest()
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
                    onDismissRequest()
                }
            ) {
                AlertDismissText(
                    text = stringResource(R.string.alert_dismiss)
                )
            }
        }
    )

}


fun applyDateMask(input: String): TextFieldValue {
    var newText = input
    if (input.length == 2) {
        newText += "."
    }


    var maskedInput = input
    if (maskedInput.length == 2)
        maskedInput += "."
    if (maskedInput.length == 5)
        maskedInput += "."

    val finalText = if (maskedInput.length > 10) maskedInput.substring(0, 10) else maskedInput
    Log.d("INPUTALERT", finalText)
    Log.d("INPUTALERT", finalText.length.toString())
    return TextFieldValue(
        text = finalText,
        selection = TextRange(1)
    )
}


fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


@Preview
@Composable
private fun Alert() {
    //DatePickerDocked()
}