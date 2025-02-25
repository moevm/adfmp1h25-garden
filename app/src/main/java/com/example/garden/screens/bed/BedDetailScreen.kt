package com.example.garden.screens.bed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.screens.navigation.Destination

@Composable
fun BedDetailScreen(navController: NavHostController, viewModel: BedViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(50.dp)) {
        Text(text = "Bed Detail")
        Button(onClick = {
            navController.navigate(Destination.Home.route)
        }) {
            Text("back")
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
        viewModel.listStat.collectAsState().value.forEach { el->
            Text(text = el.bed_id)
        }
    }
}