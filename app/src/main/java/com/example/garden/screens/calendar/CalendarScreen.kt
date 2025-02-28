package com.example.garden.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.screens.navigation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navController: NavHostController,
    calendarViewModel: CalendarViewModel = hiltViewModel()
) {
    val datePickerState = rememberDatePickerState()
    var showDropDown by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(
                text = stringResource(calendarViewModel.getMonth())
            )
            IconButton(
                onClick = {
                    showDropDown = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(70.dp))
            DropdownMenu(
                expanded = showDropDown,
                onDismissRequest = {
                    showDropDown = false
                },
                modifier = Modifier
                    .width(100.dp)
                    .height(350.dp)
            ) {
                Column {
                    calendarViewModel.listMonth.value.forEachIndexed { index, month ->
                        Text(
                            modifier = Modifier.clickable {
                                calendarViewModel.setMonth(index)
                                showDropDown = false
                            },
                            text = stringResource(month)
                        )
                    }
                }
            }

            IconButton(
                onClick = {
                    calendarViewModel.decYear()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .rotate(90f)
                )
            }
            Text(
                text = calendarViewModel.year.collectAsState().value.toString()
            )

            IconButton(
                onClick = {
                    calendarViewModel.incYear()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .rotate(-90f)
                )
            }

            Button(onClick = {
                calendarViewModel.addNotification()
            }) {
                Text(
                    "add",
                    color = colorScheme.background
                )
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            calendarViewModel.listWeek.value.forEach { day ->
                Text(
                    text = stringResource(day),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            items(calendarViewModel.listDays.value){ day ->
                Box(){
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(percent = 30))
                            .background(day.second)
                            .padding(5.dp)
                            .fillMaxSize()
                        ,
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = if(day.first<=0) "" else day.first.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    if(day.first>0)
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color.Red)
                            .size(11.dp)
                            .align(Alignment.TopEnd)
                        )
                }

            }


        }




        Button(onClick = {
            navController.navigate(Destination.NotificationDate.route)
        }) {
            Text(
                "notification date",
                color = colorScheme.background
            )
        }
        calendarViewModel.listNotification.collectAsState().value.forEach { el ->
            Text(text = el.title)

        }
    }
}