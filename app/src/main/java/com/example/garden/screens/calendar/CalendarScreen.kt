package com.example.garden.screens.calendar

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.models.Day
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.AddNotificationAlertDialog
import com.example.garden.screens.widgets.text.DropMenuText
import com.example.garden.ui.theme.FontBlackColor
import com.example.garden.ui.theme.FontGrayColor
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.Red
import com.example.garden.ui.theme.White
import java.util.Date


@Composable
fun CalendarScreen(
    navController: NavHostController,
    dbViewModel: DBViewModel,
    calendarViewModel: CalendarViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            val context = LocalContext.current
            MonthYearPicker(
                month = calendarViewModel.getMonth(),
                year = calendarViewModel.year.collectAsState().value,
                listMonth = calendarViewModel.listMonth.value,
                changeMonth = calendarViewModel::setMonth,
                decYear = {
                    if (!calendarViewModel.decYear()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.too_early_a_year), Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                incYear = {
                    if (!calendarViewModel.incYear()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.too_late_a_year), Toast.LENGTH_SHORT
                        ).show()
                    }
                },
            )
            AddNotificationButton(
                addNotification = dbViewModel::addNotification,
                listBeds = dbViewModel.listBeds.collectAsState().value
            )
        }

        HorizontalDivider(
            color = LightGreen,
            thickness = 2.dp,
            modifier = Modifier
                .padding(vertical = 15.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            DayOfWeek(calendarViewModel.listWeek.value)
            DateGrid(
                calendarViewModel.listDays.collectAsState().value,
                onClick = {
                    dbViewModel.saveDate(calendarViewModel.getDate(it))
                    navController.navigate(Destination.NotificationDate.route)
                }
            )
            Legend(calendarViewModel.legend.value)
        }

//        calendarViewModel.listNotification.collectAsState().value.forEach { el ->
//            Text(text = el.title)
//
//        }
    }
}

@Composable
fun Legend(
    legend: List<Pair<Int, Color>>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        legend.forEach { line ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(
                            line.second
                        )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(line.first),
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = FontBlackColor
                )
            }
        }
    }
}

@Composable
fun DayOfWeek(listWeek: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        listWeek.forEach { day ->
            Text(
                text = stringResource(day),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.alpha(70f),
                color = FontGrayColor
            )
        }
    }
}

@Composable
fun MonthYearPicker(
    month: Int,
    year: Int,
    listMonth: List<Int>,
    changeMonth: (Int) -> Unit,
    decYear: () -> Unit,
    incYear: () -> Unit
) {
    var showDropDown by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = stringResource(month),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = FontBlackColor
        )
        IconButton(
            onClick = {
                showDropDown = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
        DropdownMenu(
            expanded = showDropDown,
            onDismissRequest = {
                showDropDown = false
            },
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 30))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                listMonth.forEachIndexed { index, month ->
                    DropMenuText(
                        stringResource(month)
                    ) {
                        changeMonth(index)
                        showDropDown = false
                    }
//                    Text(
//                        modifier = Modifier.clickable {
//                            changeMonth(index)
//                            showDropDown = false
//                        },
//                        text = stringResource(month),
//                        fontSize = 18.sp,
//                        color = FontBlackColor
//                    )
                }
            }
        }

        IconButton(
            onClick = decYear
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .rotate(90f)
            )
        }
        Text(
            text = year.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = FontBlackColor
        )

        IconButton(
            onClick = incYear
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .rotate(-90f)
            )
        }

    }
}

@Composable
fun AddNotificationButton(
    addNotification: (
        title: String,
        bed_id: String,
        description: String,
        dateStart: Date,
        dateEnd: Date,
    ) -> Unit,
    listBeds: List<Bed>
) {
    var addAlert by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val alert_message = stringResource(R.string.create_bed_to_add_notification)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = {
                if (listBeds.isNotEmpty()) {
                    addAlert = true
                } else {
                    Toast.makeText(
                        context,
                        alert_message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 50))
                .background(LightGreen),

            ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = White,

                )
        }
    }

    if (addAlert && listBeds.isNotEmpty())
        AddNotificationAlertDialog(
            onDismissRequest = {
                addAlert = false
            },
            onConfirmation = addNotification,
            listBeds = listBeds
        )


}


@Preview
@Composable
private fun Prev() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MonthYearPicker(
            R.string.may,
            2025,
            listMonth = listOf(1, 2, 3),
            changeMonth = {
            },
            decYear = {},
            incYear = {}
        )
    }

}

@Composable
fun DateGrid(listDays: List<Day>, onClick: (Day) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        columns = GridCells.Fixed(7),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        items(listDays) { day ->
            Box() {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 30))
                        .background(day.color)
                        .padding(5.dp)
                        .fillMaxSize()
                        .clickable {
                            onClick(day)
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = if (day.day <= 0) "" else day.day.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = FontBlackColor
                    )
                }
                if (day.notification)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Red)
                            .size(11.dp)
                            .align(Alignment.TopEnd)
                    )
            }

        }


    }
}