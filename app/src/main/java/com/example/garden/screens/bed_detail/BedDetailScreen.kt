package com.example.garden.screens.bed_detail

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.screens.widgets.text.TitleText
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White

@Composable
fun BedDetailScreen(navController: NavHostController, viewModel: DBViewModel) {
    val bed by viewModel.bed.collectAsState()

    LaunchedEffect(viewModel) {

    }
    Column(modifier = Modifier.fillMaxSize().background(White)) {
        Box (modifier = Modifier.fillMaxWidth().padding(top = 50.dp, start = 20.dp, end = 20.dp)){
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                TitleText(bed.title)

            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End) {
                IconButton(
                    onClick = {},
                ) {
                    Icon(imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = DarkGreen,
                        modifier = Modifier.size(35.dp))
                }
            }
        }


        HorizontalDivider(
            color = LightGreen,
            thickness = 2.dp,
            modifier = Modifier
                .padding(vertical = 15.dp)

        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),

            ) {

            ChapterText(stringResource(R.string.gallery))
            ChapterText(stringResource(R.string.statistics))
            ChapterText(stringResource(R.string.changes))
        }
        Button(onClick = {
            navController.navigate(Destination.BedEdit.route)
        }) {
            Text("edit")
        }
        Button(onClick = {
            viewModel.addStat()
        }) {

        }
        Text("info")


        Text("stat")
        viewModel.listStatBed.collectAsState().value.forEach { el->
            Text(text = el.bed_id)
        }
    }
}