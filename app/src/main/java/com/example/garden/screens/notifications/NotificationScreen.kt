package com.example.garden.screens.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.models.Notifications
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.NotificationCard
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.screens.widgets.text.TitleText
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(
    navController: NavHostController,
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

        return@filter datesDifference.days == 0 && it.title.lowercase()
            .contains(textInput.lowercase())

    }
    val onWeekNotifications = dbViewModel.notifications.collectAsState().value.filter {
        val notificationDate = it.dateStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val datesDifference = Period.between(notificationDate, currentDate)

        return@filter datesDifference.days < 7 && it.title.lowercase()
            .contains(textInput.lowercase())

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 65.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TitleText(
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
            NotificationList(
                label = stringResource(R.string.today),
                todayNotifications,
                onClickCard = {
                    dbViewModel.saveNote(it)
                    navController.navigate(Destination.NotificationDetail.route)
                },
                onDeleteClick = {
                    dbViewModel.deleteNotification(it)
                })
            NotificationList(
                label = stringResource(R.string.on_week),
                onWeekNotifications,
                onClickCard = {
                    dbViewModel.saveNote(it)
                    navController.navigate(Destination.NotificationDetail.route)
                },
                onDeleteClick = {
                    dbViewModel.deleteNotification(it)
                })
        }
        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
fun NotificationList(
    label: String,
    notifications: List<Notifications>,
    onClickCard: (Notifications) -> Unit,
    onDeleteClick: (Notifications) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ChapterText(
            text = label,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            notifications.forEach { item ->
                NotificationCard(
                    title = item.title,
                    dateStart = item.dateStart,
                    dateEnd = item.dateEnd,
                    modifier = Modifier.clickable {
                        onClickCard(item)
                    },
                    onDeleteClick = { onDeleteClick(item) }
                )
            }
        }
    }
}

