package com.example.garden.screens.bed_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination

@Composable
fun BedsListScreen(
    navController: NavHostController,
    viewModel: DBViewModel,
    bedListViewModel: BedListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        Text(text = "BedList")

        Button(onClick = {
            navController.navigate(Destination.BedCreating.route)
        }) {
            Text("add")
        }

        viewModel.listBeds.collectAsState().value.forEach { el ->
            Column(modifier = Modifier.clickable {
                //Toast.makeText(context, el.id.toString(), Toast.LENGTH_SHORT).show()
                viewModel.saveBed(el)
                viewModel.getStatByBedId(el.id.toString())
                navController.navigate(Destination.BedDetail.route)
            }) {
                Button(onClick = {
                    viewModel.archiveBed(el)
                }) {
                    Text("delete")
                }
                Text(text = el.title)
                Text(text = el.id.toString())
            }

        }
    }
}