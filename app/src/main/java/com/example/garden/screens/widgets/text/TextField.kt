package com.example.garden.screens.widgets.text

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.garden.models.Bed
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.FontBlackColor
import com.example.garden.ui.theme.FontGrayColor
import com.example.garden.ui.theme.Gray
import com.example.garden.ui.theme.White

@Composable
fun TitleTextField(
    value: String,
    label: String,
    onChange: (String) -> Unit,
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
        maxLines = 1,
        label = { Text(text = label) },
        textStyle = TextStyle.Default.copy(
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = FontBlackColor
        ),
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = if (imeAction == ImeAction.Done)
            KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ) else KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )
    )
}

@Composable
fun ContentTextField(
    value: String,
    label: String,
    onChange: (String) -> Unit,
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
        label = { Text(text = label) },
        textStyle = TextStyle.Default.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = FontBlackColor,
        ),
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = if (isNumber) KeyboardType.Number else KeyboardType.Text
        ),
        keyboardActions = if (imeAction == ImeAction.Done)
            KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ) else KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataTextField(
    value: String,
    label: String,
    datePickerState: DatePickerState,
    imeAction: ImeAction = ImeAction.Next,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var showDatePicker by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { },
        label = { Text(text = label) },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = {
                focusRequester.requestFocus()
                showDatePicker = !showDatePicker
            }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Select date"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            unfocusedLabelColor = FontGrayColor,
            focusedLabelColor = FontBlackColor,
            unfocusedIndicatorColor = DarkGreen,
            focusedIndicatorColor = DarkGreen
        ),
        textStyle = TextStyle.Default.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = FontBlackColor,
        ),
        keyboardActions = if (imeAction == ImeAction.Done)
            KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ) else KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )
    )

    if (showDatePicker) {
        Popup(
            onDismissRequest = { showDatePicker = false },
            alignment = Alignment.Center,
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                excludeFromSystemGesture = true,
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray.copy(0.5f))
            ) {
                // Your content code is here

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .offset(y = 64.dp)
//                        .shadow(elevation = 4.dp)
                        .background(White)
                        .padding(16.dp)
                ) {
                    IconButton(
                        onClick = {
                            showDatePicker = false
                            focusManager.moveFocus(FocusDirection.Down)
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
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

@Composable
fun ListTextField(
    value: String,
    listBeds: List<Bed>,
    label: String,
    onChange: (Bed) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
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
                    focusRequester.requestFocus()
                    showDropDown = !showDropDown

                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Select bed"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedContainerColor = White,
                unfocusedLabelColor = FontGrayColor,
                focusedLabelColor = FontBlackColor,
                unfocusedIndicatorColor = DarkGreen,
                focusedIndicatorColor = DarkGreen
            ),
            keyboardOptions = KeyboardOptions(imeAction = imeAction),
            keyboardActions = if (imeAction == ImeAction.Done)
                KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ) else KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            textStyle = TextStyle.Default.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = FontBlackColor,
            ),
        )
        DropdownMenu(
            expanded = showDropDown,
            onDismissRequest = {
                showDropDown = false
            },
            modifier = Modifier
                .background(White)
                .heightIn(0.dp, 300.dp)
                .clip(RoundedCornerShape(percent = 30))


        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                listBeds.forEach { bed ->
                    DropMenuText(bed.title) {
                        onChange(bed)
                        showDropDown = false
                        if (imeAction == ImeAction.Done)
                            focusManager.clearFocus()
                        else
                            focusManager.moveFocus(FocusDirection.Down)
                    }
//                    Text(
//                        modifier = Modifier.clickable {
//                            onChange(bed)
//                            showDropDown = false
//                        },
//                        text = bed.title,
//                        fontSize = 18.sp,
//                        color = FontBlackColor
//                    )
                }
            }
        }
    }
}