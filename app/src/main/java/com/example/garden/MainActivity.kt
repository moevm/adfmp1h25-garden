package com.example.garden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.garden.screens.bed.BedViewModel
import com.example.garden.screens.navigation.SetUpOnGraph
import com.example.garden.ui.theme.GardenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            val bedViewModel = hiltViewModel<BedViewModel>()
            GardenTheme {
                SetUpOnGraph(navController,bedViewModel)
            }
        }
    }
}
