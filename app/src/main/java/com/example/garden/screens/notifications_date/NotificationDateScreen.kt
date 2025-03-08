package com.example.garden.screens.notifications_date

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.AddNotificationAlertDialog
import com.example.garden.screens.widgets.BottomButton
import com.example.garden.screens.widgets.NotificationCard
import com.example.garden.screens.widgets.text.TitleText
import com.example.garden.ui.theme.LightGreen
import java.text.SimpleDateFormat


@Composable
fun NotificationDateScreen(navController: NavHostController, dbViewModel: DBViewModel) {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    val sdf_year = SimpleDateFormat("yyyy")
    val sdf_month = SimpleDateFormat("MM")
    val sdf_day = SimpleDateFormat("dd")
    val date = dbViewModel.date.collectAsState().value
    val filtered = dbViewModel.notifications.collectAsState().value.filter {
        sdf_year.format(it.dateStart).toInt() <= sdf_year.format(date).toInt() &&
                sdf_year.format(date).toInt() <= sdf_year.format(it.dateEnd).toInt() &&
                sdf_month.format(it.dateStart).toInt() <= sdf_month.format(date).toInt() &&
                sdf_month.format(date).toInt() <= sdf_month.format(it.dateEnd).toInt() &&
                sdf_day.format(it.dateStart).toInt() <= sdf_day.format(date).toInt() &&
                sdf_day.format(date).toInt() <= sdf_day.format(it.dateEnd).toInt()
    }

    var addAlert by remember {
        mutableStateOf(false)
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
                navController.navigate(Destination.Home.route)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                    contentDescription = null,

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))
            TitleText(sdf.format(date))
            //TitleText(bed.id.toString())
        }

        HorizontalDivider(
            color = LightGreen,
            thickness = 2.dp,
            modifier = Modifier
                .padding(top = 15.dp)

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(15.dp))
            filtered.forEach { item ->
                NotificationCard(title = item.title,
                    dateStart = item.dateStart,
                    dateEnd = item.dateEnd,
                    modifier = Modifier.clickable {
                        dbViewModel.saveNote(item)
                        navController.navigate(Destination.NotificationDetail.route)
                    },
                    onDeleteClick ={  dbViewModel.deleteNotification(item)}
                )
            }
            Spacer(Modifier.height(50.dp))

        }

    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 50.dp, start = 20.dp, end = 20.dp)) {
        BottomButton(
            text = stringResource(R.string.add),
            onClick = {
                addAlert = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }

    if (addAlert)
        AddNotificationAlertDialog(
            onDismissRequest = {
                addAlert = false
            },
            onConfirmation = dbViewModel::addNotification,
            listBeds = dbViewModel.listBeds.collectAsState().value
        )
}