package com.example.garden.screens.widgets.text

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.garden.models.Bed
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.FontBlackColor
import com.example.garden.ui.theme.FontGrayColor
import com.example.garden.ui.theme.White

@Composable
fun AlertListTextField(
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
            )
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

@Composable
fun AlertListReasonTextField(
    value: String,
    list: List<Int>,
    label: String,
    onChange: (Int) -> Unit,
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
            )
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
                list.forEach { el ->
                    DropMenuText(stringResource(el)) {
                        onChange(el)
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