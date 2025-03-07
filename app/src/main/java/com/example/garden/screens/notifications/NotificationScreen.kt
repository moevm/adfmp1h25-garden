package com.example.garden.screens.notifications

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.garden.screens.DBViewModel
import com.example.garden.R
import com.example.garden.models.Notifications

import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.text.ChapterText
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(
    navController:NavHostController,
    dbViewModel: DBViewModel,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {
    var textInput by remember {
        mutableStateOf("")
    }

    val currentDate = LocalDate.now()
    val todayNotifications = dbViewModel.notifications.collectAsState().value.filter {
        val notificationDate = it.dateStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val datesDifference = Period.between(notificationDate, currentDate)

        return@filter datesDifference.days == 0 && it.title.contains(textInput)
    }
    val onWeekNotifications = dbViewModel.notifications.collectAsState().value.filter {
        val notificationDate = it.dateStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val datesDifference = Period.between(notificationDate, currentDate)

        return@filter datesDifference.days < 7 && it.title.contains(textInput)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ChapterText(
            text = stringResource(R.string.notifications)
        )
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.magnifyingg), null) },
            label = { Text(stringResource(R.string.search)) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(Modifier.height(9.dp))
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            NotificationList(label = stringResource(R.string.today), todayNotifications)
            NotificationList(label = stringResource(R.string.on_week), onWeekNotifications)
        }
    }
}

@Composable
fun NotificationList(label: String, notifications: List<Notifications>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(start = 5.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            notifications.forEach { item ->
                NotificationCard(title = item.title, dateStart = item.dateStart, dateEnd = item.dateEnd)
            }
        }
    }
}

@Composable
fun NotificationCard(title: String, dateStart: Date, dateEnd: Date) {
    // TODO: is it ok?????
    val sdf = SimpleDateFormat("dd.MM.yyyy")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Column(
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cross),
                null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            )
        }
        Column() {
            Text(
                text = title,
                fontSize = 13.sp
            )
            Row() {
                Column() {
                    Text(
                        text = stringResource(R.string.start),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = stringResource(R.string.end),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.width(2.dp))
                Column() {
                    Text(
                        text = sdf.format(dateStart),
                        fontSize = 11.sp
                    )
                    Text(
                        text = sdf.format(dateEnd),
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
