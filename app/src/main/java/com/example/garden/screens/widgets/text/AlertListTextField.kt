package com.example.garden.screens.widgets.text

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.garden.models.Bed
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.FontBlackColor
import com.example.garden.ui.theme.FontGrayColor
import com.example.garden.ui.theme.White

@Composable
fun AlertTextField(
    value:String,
    listBeds:List<Bed>,
    label:String,
    onChange:(Bed)->Unit
) {
    var showDropDown by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            label = { Text(text = label) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    showDropDown = !showDropDown

                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Select bed"
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

        DropdownMenu(
            expanded = showDropDown,
            onDismissRequest = {
                showDropDown = false
            },
            modifier = Modifier
                .background(White)
                .clip(RoundedCornerShape(percent = 30))


        ) {
            Column(modifier = Modifier
                .padding(horizontal = 20.dp)) {
                listBeds.forEach{ bed ->
                    Text(
                        modifier = Modifier.clickable {
                            onChange(bed)
                            showDropDown = false
                        },
                        text = bed.title,
                        fontSize = 18.sp,
                        color = FontBlackColor
                    )
                }
            }
        }
    }
}