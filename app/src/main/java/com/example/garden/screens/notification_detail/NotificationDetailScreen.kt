package com.example.garden.screens.notification_detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.models.Notifications
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.convertMillisToDate
import com.example.garden.screens.widgets.text.ContentTextField
import com.example.garden.screens.widgets.text.DataTextField
import com.example.garden.screens.widgets.text.ListTextField
import com.example.garden.screens.widgets.text.TitleTextField
import com.example.garden.ui.theme.LightGreen
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationDetailScreen(
    navController: NavHostController,
    dbViewModel: DBViewModel,
    notificationDetailViewModel: NotificationDetailViewModel = hiltViewModel()
) {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    val note = dbViewModel.note.collectAsState().value
    val title = notificationDetailViewModel.title.value
    val dateStart = notificationDetailViewModel.dateStart.value
    val dateEnd = notificationDetailViewModel.dateEnd.value
    val desc = notificationDetailViewModel.desc.value
    val bed = notificationDetailViewModel.bed.value

    val datePickerStart = rememberDatePickerState()
    notificationDetailViewModel.changeDateStart(datePickerStart.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: "")

    val datePickerEnd = rememberDatePickerState()
    notificationDetailViewModel.changeDateEnd(datePickerEnd.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: "")

    LaunchedEffect(note) {
        dbViewModel.getBedById(note.bed_id.toUpperCase())
        notificationDetailViewModel.changeTitle(note.title)
        notificationDetailViewModel.changeDesc(note.description)
        datePickerStart.selectedDateMillis = note.dateStart.time
        datePickerEnd.selectedDateMillis = note.dateEnd.time
    }
    LaunchedEffect(dbViewModel.bedById) {
        Log.d("BEDBYID", dbViewModel.bedById.value.toString())
        if (dbViewModel.bedById.value != null) {
            notificationDetailViewModel.changeBed(dbViewModel.bedById.value!!)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                dbViewModel.updateNotification(
                    Notifications(
                        id = note.id,
                        dateStart = datePickerStart.selectedDateMillis?.let { Date(it) } ?: Date(0),
                        dateEnd = datePickerEnd.selectedDateMillis?.let { Date(it) } ?: Date(0),
                        title = title,
                        description = desc,
                        bed_id = note.bed_id
                    )

                )
                navController.navigate(Destination.Home.route)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                    contentDescription = null,
                )
            }
            //TitleText(bed.id.toString())
        }
        HorizontalDivider(
            color = LightGreen,
            thickness = 2.dp,
            modifier = Modifier
                .padding(vertical = 15.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            TitleTextField(
                label = "",
                value = title,
                onChange = notificationDetailViewModel::changeTitle
            )
            DataTextField(
                label = stringResource(R.string.alert_add_event_start) + ": ",
                value = dateStart,
                datePickerState = datePickerStart
            )
            DataTextField(
                label = stringResource(R.string.alert_add_event_end) + ": ",
                value = dateEnd,
                datePickerState = datePickerEnd
            )
            ListTextField(
                listBeds = dbViewModel.listBeds.collectAsState().value,
                label = stringResource(R.string.alert_add_event_bed) + ": ",
                value = if (bed != null) bed.title else "",
                onChange = notificationDetailViewModel::changeBed
            )
            ContentTextField(
                label = stringResource(R.string.alert_add_event_description) + ": ",
                value = desc,
                onChange = notificationDetailViewModel::changeDesc,
                imeAction = ImeAction.Done
            )
            //ContentText(stringResource(R.string.alert_add_event_start)+": " + sdf.format(note.dateStart))
            //ContentText(stringResource(R.string.alert_add_event_end) +": "+ sdf.format(note.dateEnd))
            //ContentText(stringResource(R.string.alert_add_event_bed) +": "+note.bed_id)
            //ContentText(stringResource(R.string.alert_add_event_description) +": \n"+note.description)
        }
    }
}