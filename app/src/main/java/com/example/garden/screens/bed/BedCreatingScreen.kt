package com.example.garden.screens.bed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.garden.screens.navigation.Destination

@Composable
fun BedCreatingScreen(navController: NavHostController) {
    Column (){
        Button(onClick = {
            navController.navigate(Destination.Home.route)
        }) {
            Text("back")
        }
        Text(
            text = "BedCreating"
        )
    }
}