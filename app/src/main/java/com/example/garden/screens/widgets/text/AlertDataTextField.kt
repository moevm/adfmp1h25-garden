package com.example.garden.screens.widgets.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.FontBlackColor
import com.example.garden.ui.theme.FontGrayColor
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDataTextField(value:String,  label:String,datePickerState: DatePickerState) {
    var showDatePicker by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            label = { Text(text = label) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedContainerColor = White,
                unfocusedLabelColor = FontGrayColor,
                focusedLabelColor = FontBlackColor,
                unfocusedIndicatorColor = DarkGreen,
                focusedIndicatorColor = DarkGreen
            )
        )

        if (showDatePicker) {
            Box(){
                Popup(
                    onDismissRequest = {  },
                    alignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
//                        .offset(y = 64.dp)
//                        .shadow(elevation = 4.dp)
                            .background(White)
                            .padding(16.dp)
                    ) {
                        IconButton(onClick = {
                            showDatePicker = false
                        },
                            modifier = Modifier.align(Alignment.End)) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false,
                            colors = DatePickerDefaults.colors(
                                selectedDayContainerColor = DarkGreen,
                                todayDateBorderColor = DarkGreen,
                                todayContentColor = DarkGreen
                            )
                        )
                    }
                }
            }

        }
    }
}
