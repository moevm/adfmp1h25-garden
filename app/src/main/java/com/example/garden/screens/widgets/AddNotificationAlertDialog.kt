package com.example.garden.screens.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.garden.R
import com.example.garden.screens.widgets.text.AlertConfirmText
import com.example.garden.screens.widgets.text.AlertContentText
import com.example.garden.screens.widgets.text.AlertDataTextField
import com.example.garden.screens.widgets.text.AlertDismissText
import com.example.garden.screens.widgets.text.AlertTextField
import com.example.garden.screens.widgets.text.AlertTitle
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.Red
import com.example.garden.ui.theme.White

@Composable
fun AddNotificationAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,

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
        mutableStateOf("")
    }
    AlertDialog(
        containerColor = White,
        title = {
            AlertTitle(text = stringResource(R.string.alert_add_event_title))
        },
        text = {
            Column() {
                Row(verticalAlignment = Alignment.CenterVertically){
                    AlertContentText(stringResource(R.string.alert_add_event_name)+":")
                    Spacer(Modifier.width(10.dp))
                    AlertTextField(
                        value = name,
                        onChange = {name = it}
                    )
                }
                Row (verticalAlignment = Alignment.CenterVertically){
                    AlertContentText(stringResource(R.string.alert_add_event_start)+":")
                    AlertDataTextField(
                        value = dateStart,
                        onChange = {dateStart = applyDateMask(it).text},

                    )
                }

                AlertContentText(stringResource(R.string.alert_add_event_end)+":")

                Row (verticalAlignment = Alignment.CenterVertically){
                    AlertContentText(stringResource(R.string.alert_add_event_description)+":")
                    AlertTextField(
                        value = description,
                        onChange = {description = it}
                    )
                }


                AlertContentText(stringResource(R.string.alert_add_event_bed)+":")
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
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
    val cleanedInput = input.filter { it.isDigit() } // Оставляем только цифры
    val maskedText = buildString {
        for (i in cleanedInput.indices) {
            when (i) {
                2, 4 -> append(".") // Добавляем точки после дня и месяца
            }
            append(cleanedInput[i])
        }
    }

    // Ограничиваем длину ввода (день.месяц.год = 10 символов)
    val finalText = if (maskedText.length > 10) maskedText.substring(0, 10) else maskedText

    return TextFieldValue(
        text = finalText,
        selection = TextRange(finalText.length) // Устанавливаем курсор в конец текста
    )
}

@Preview
@Composable
private fun Alert() {
   // AddNotificationAlertDialog({},{},"","")
}