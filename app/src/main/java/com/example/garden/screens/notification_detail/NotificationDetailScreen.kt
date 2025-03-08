package com.example.garden.screens.notification_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.garden.screens.widgets.text.ContentText
import com.example.garden.screens.widgets.text.TitleText
import com.example.garden.ui.theme.LightGreen
import java.text.SimpleDateFormat

@Composable
fun NotificationDetailScreen(navController: NavHostController,dbViewModel: DBViewModel) {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    val note = dbViewModel.note.collectAsState().value
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 50.dp)) {
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

            //TitleText(bed.id.toString())
        }

        HorizontalDivider(
            color = LightGreen,
            thickness = 2.dp,
            modifier = Modifier
                .padding(vertical = 15.dp)

        )

        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            TitleText(note.title)
            ContentText(stringResource(R.string.alert_add_event_start)+": " + sdf.format(note.dateStart))
            ContentText(stringResource(R.string.alert_add_event_end) +": "+ sdf.format(note.dateEnd))
            ContentText(stringResource(R.string.alert_add_event_bed) +": "+note.bed_id)
            ContentText(stringResource(R.string.alert_add_event_description) +": \n"+note.description)
        }
    }
}