package com.example.garden.screens.archive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ArchiveScreen(
    archiveViewModel: ArchiveViewModel = hiltViewModel()
) {
    Column(){
        Text(
            text = "Archive"
        )
        archiveViewModel.archiveList.collectAsState().value.forEach { el->
            Button(onClick = {
                archiveViewModel.delete(el)
            }) {
                Text("delete")
            }
            Button(onClick = {

                archiveViewModel.update(el)
            }) {
                Text("восстановить")
            }
            Text(text = el.title)
            Text(text = el.id.toString())

        }
    }
}