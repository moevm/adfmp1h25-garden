package com.example.garden.screens.bed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import com.example.garden.screens.navigation.Destination

@Composable
fun BedDetailScreen(viewModel: BedViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize().padding(50.dp)) {
        Text(text = "Bed Detail")
        Button(onClick = {
            viewModel.addStat()
        }) {

        }
        viewModel.listStat.collectAsState().value.forEach { el->
            Text(text = el.bed_id)
        }
    }
}