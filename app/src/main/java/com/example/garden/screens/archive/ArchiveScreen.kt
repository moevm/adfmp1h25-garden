package com.example.garden.screens.archive

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.ArchiveAlertDialog
import com.example.garden.screens.widgets.BedItem
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
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 50.dp)) {
        TitleText(
            text = stringResource(R.string.archive)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            Spacer(modifier = Modifier.size(20.dp))
            archiveList.forEach { bed ->

                BedItem(
                    bed = bed,
                    onDeleteClick = {

                        dbViewModel.deleteBed(bed)
                    },
                    modifier = Modifier.clickable {
                        archiveViewModel.changeWarningShow(true)
                        archiveViewModel.saveBed(bed)
                    }
                )
            }
            Spacer(modifier = Modifier.size(60.dp))
        }
    }

    if (archiveViewModel.showWarning.value) {
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