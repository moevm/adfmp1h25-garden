package com.example.garden.screens.archive

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garden.R
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.widgets.ArchiveAlertDialog
import com.example.garden.screens.widgets.BedItem
import com.example.garden.screens.widgets.WarningAlertDialog
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.screens.widgets.text.TitleText

@Composable
fun ArchiveScreen(
    dbViewModel: DBViewModel,
    archiveViewModel: ArchiveViewModel = hiltViewModel()
) {
    val archiveList = dbViewModel.archiveList.collectAsState().value
    val bed = archiveViewModel.bed.collectAsState().value

    val archiveListThisYear = archiveViewModel.archiveListThisYear.collectAsState().value
    val archiveListPrevYear = archiveViewModel.archiveListPrevYear.collectAsState().value
    val archiveListOtherYear = archiveViewModel.archiveListOtherYear.collectAsState().value

    LaunchedEffect(archiveList) {
        archiveViewModel.filter(archiveList)
    }
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
            ChapterText(stringResource(R.string.this_year))
            archiveListThisYear.forEach { bed ->

                BedItem(
                    bed = bed,
                    onDeleteClick = {
                        archiveViewModel.changeWarningDeleteShow(true)
                        archiveViewModel.saveBed(bed)
                    },
                    modifier = Modifier.clickable {
                        archiveViewModel.changeWarningResolveShow(true)
                        archiveViewModel.saveBed(bed)
                    }
                )
            }

            ChapterText(stringResource(R.string.previous_year))
            archiveListPrevYear.forEach { bed ->

                BedItem(
                    bed = bed,
                    onDeleteClick = {
                        archiveViewModel.changeWarningDeleteShow(true)
                        archiveViewModel.saveBed(bed)
                    },
                    modifier = Modifier.clickable {
                        archiveViewModel.changeWarningResolveShow(true)
                        archiveViewModel.saveBed(bed)
                    }
                )
            }

            ChapterText(stringResource(R.string.other_years))
            archiveListOtherYear.forEach { bed ->
                BedItem(
                    bed = bed,
                    onDeleteClick = {
                        archiveViewModel.changeWarningDeleteShow(true)
                        archiveViewModel.saveBed(bed)
                    },
                    modifier = Modifier.clickable {
                        archiveViewModel.changeWarningResolveShow(true)
                        archiveViewModel.saveBed(bed)
                    }
                )
            }
            Spacer(modifier = Modifier.size(60.dp))
        }
    }

    if (archiveViewModel.showWarningResolve.value) {
        bed?.title?.let {
            ArchiveAlertDialog(
                onConfirm = {
                    dbViewModel.restoreBed(bed)
                    archiveViewModel.changeWarningResolveShow(false)
                },
                onDismiss = {
                    archiveViewModel.changeWarningResolveShow(false)
                },
                bed_title = it
            )
        }
    }
    if (archiveViewModel.showWarningDelete.value) {
        bed?.title?.let {
            WarningAlertDialog(
                title = stringResource(R.string.sure_to_delete) + " " + bed.title + "?",
                onConfirm = {
                    dbViewModel.deleteBed(bed)
                    archiveViewModel.changeWarningDeleteShow(false)
                },
                onDismiss = { archiveViewModel.changeWarningDeleteShow(false) }
            )
        }
    }
}