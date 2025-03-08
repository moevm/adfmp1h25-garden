package com.example.garden.screens.archive

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garden.R
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.widgets.ArchiveAlertDialog
import com.example.garden.screens.widgets.text.TitleText

@Composable
fun ArchiveScreen(
    dbViewModel: DBViewModel,
    archiveViewModel: ArchiveViewModel = hiltViewModel()
) {
    val archiveList = dbViewModel.archiveList.collectAsState().value
    val bed = archiveViewModel.bed.collectAsState().value

//    LaunchedEffect(true) {
//        archiveViewModel.getFilterDates(archiveList)
//    }
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 50.dp, vertical = 20.dp)
            .verticalScroll(rememberScrollState()),

        ){
        TitleText(
            text = stringResource(R.string.archive)
        )
        archiveList.forEach { el->

            Button(onClick = {
                dbViewModel.deleteBed(el)
            }) {
                Text("delete")
            }
            Button(onClick = {
                archiveViewModel.changeWarningShow(true)
                archiveViewModel.saveBed(el)
                //dbViewModel.restoreBed(el)
            }) {
                Text("восстановить")
            }
            Text(text = el.title)
            Text(text = el.id.toString())

        }
    }
    if(archiveViewModel.showWarning.value){
        bed?.title?.let {
            ArchiveAlertDialog(
                onConfirm = {
                    dbViewModel.restoreBed(bed)
                    archiveViewModel.changeWarningShow(false)
                },
                onDismiss = {
                    archiveViewModel.changeWarningShow(false)
                },
                bed_title = it
            )
        }
    }

}