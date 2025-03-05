package com.example.garden.screens.bed_creating

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination

@Composable
fun BedCreatingScreen(navController: NavHostController, dbViewModel: DBViewModel) {
    Column (){
        Button(onClick = {
            navController.navigate(Destination.Home.route)
        }) {
            Text("back")
        }
        Button(onClick = {
            dbViewModel.addBed()
        }) {
            Text("add")
        }
        Text(
            text = "BedCreating"
        )
    }
}