package com.example.garden.screens.beds_list

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

@Composable
fun BedsListScreen(viewModel: BedListViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize().padding(50.dp)) {
        Text(text = "BedList")
        Button(onClick = {
            viewModel.add()
        }) {

        }
        viewModel.listBeds.collectAsState().value.forEach { el->
            Text(text = el.title)
        }
    }
}