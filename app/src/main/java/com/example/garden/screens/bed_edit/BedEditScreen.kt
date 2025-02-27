package com.example.garden.screens.bed_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.garden.screens.navigation.Destination

@Composable
fun BedEditScreen(navController: NavHostController) {
    Column (){
        Button(onClick = {
            navController.navigate(Destination.Home.route)
        }) {
            Text("back")
        }
        Text(
            text = "BedEdit"
        )
    }
}