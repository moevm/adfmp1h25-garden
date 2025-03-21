package com.example.garden.screens.bed_detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.AddNotificationAlertDialog
import com.example.garden.screens.widgets.ChangesAlertDialog
import com.example.garden.screens.widgets.ImageAlertDialog
import com.example.garden.screens.widgets.text.ContentText
import com.example.garden.screens.widgets.text.DropMenuText
import com.example.garden.screens.widgets.text.TitleText
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White
import java.text.SimpleDateFormat

@Composable
fun BedDetailScreen(
    navController: NavHostController,
    dbViewModel: DBViewModel,
    bedDetailViewModel: BedDetailViewModel = hiltViewModel()
) {
    val bed by dbViewModel.bed.collectAsState()
    val alertImageShow by bedDetailViewModel.alertImageShow.collectAsState()
    val alertAddNotificationShow by bedDetailViewModel.alertAddNotificationShow.collectAsState()
    val showDropDown by bedDetailViewModel.showDropDown.collectAsState()
    val alertShowAddChanges by bedDetailViewModel.alertShowAddChanges.collectAsState()

    val context = LocalContext.current

    val listChange = dbViewModel.listChangesBed.collectAsState().value
    val listGallery = dbViewModel.listGalleryBed.collectAsState().value

    Log.d("IMAGE LIST", listGallery.toString())
//    var alertImageShow by remember {
//        mutableStateOf(false)
//    }
//    var alertAddNotificationShow by remember {
//        mutableStateOf(false)
//    }
//    var showDropDown by remember {
//        mutableStateOf(false)
//    }
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
        //.horizontalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 20.dp, end = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {
                    navController.navigate(Destination.Home.route)
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                        contentDescription = null,

                        )
                }
                Spacer(modifier = Modifier.width(10.dp))
                TitleText(bed.title)
                //TitleText(bed.id.toString())
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Box() {
                    IconButton(
                        onClick = {
                            bedDetailViewModel.changeShowDropDown(true)
                            //showDropDown = true
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "",
                            tint = DarkGreen,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    DropMenu(
                        showDropDown = showDropDown,
                        onDismiss = {
                            bedDetailViewModel.changeShowDropDown(false)
                            //showDropDown = false
                        },
                        onClickAddNotification = {
                            bedDetailViewModel.changeAddNotificationShow(true)
                            //alertAddNotificationShow = true
                        },
                        onClickArchive = {
                            dbViewModel.archiveBed(bed)
                            navController.navigate(Destination.Home.route)
                        },
                        onClickChangeAmount = {
                            bedDetailViewModel.changeShowAddChanges(true)
                        },
                        onClickDelete = {
                            dbViewModel.deleteBed(bed)
                            navController.navigate(Destination.Home.route)
                        },
                        onClickEdit = {
                            navController.navigate(Destination.BedEdit.route)
                        }
                    )
                }

            }
        }
        HorizontalDivider(
            color = LightGreen,
            thickness = 2.dp,
            modifier = Modifier
                .padding(vertical = 15.dp)

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
            //.horizontalScroll(rememberScrollState()),

        ) {
            ContentText(stringResource(R.string.sort) + ": " + bed.sort)
            ContentText(stringResource(R.string.amount) + ": " + bed.amount)
            ContentText(stringResource(R.string.sowing_date) + ": " + sdf.format(bed.date_sowing))
            ContentText(stringResource(R.string.description) + ": ")
            ContentText(text = bed.description)
            Gallery(
                listGallery = listGallery,
                onAddClick = {
                    bedDetailViewModel.changeImageShow(true)
                },

                )
            Statistics(
                listChange.sortedBy { it.date },
                bedDetailViewModel.getMax(listChange),
                bedDetailViewModel.getMin(listChange)
            )

            Changes(
                listChanges = listChange,
                onDeleteClick = dbViewModel::deleteChange
            )
        }
    }

    if (alertAddNotificationShow)
        AddNotificationAlertDialog(
            onDismissRequest = {
                bedDetailViewModel.changeAddNotificationShow(false)
                // alertAddNotificationShow = false
            },
            onConfirmation = dbViewModel::addNotification,
            listBeds = dbViewModel.listBeds.collectAsState().value,
            currentBed = bed
        )
    if (alertImageShow)
        ImageAlertDialog(
            onDismiss = {
                bedDetailViewModel.changeImageShow(false)
                //alertImageShow = false
            },
            onConfirmation = {
                dbViewModel.addImage(it, bed.id.toString())
                bedDetailViewModel.changeImageShow(false)
                //alertImageShow = false
            }
        )
    if (alertShowAddChanges)
        ChangesAlertDialog(
            onDismiss = {
                bedDetailViewModel.changeShowAddChanges(false)
                // alertAddNotificationShow = false
            },
            onConfirm = dbViewModel::addChanges,
        )
}


@Composable
fun DropMenu(
    showDropDown: Boolean,
    onDismiss: () -> Unit,
    onClickEdit: () -> Unit,
    onClickChangeAmount: () -> Unit,
    onClickAddNotification: () -> Unit,
    onClickArchive: () -> Unit,
    onClickDelete: () -> Unit,
) {
    DropdownMenu(
        expanded = showDropDown,
        onDismissRequest = onDismiss,
        modifier = Modifier
            .clip(RoundedCornerShape(percent = 30))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            DropMenuText(stringResource(R.string.edit_bed)) {
                onDismiss()
                onClickEdit()
            }
            DropMenuText(stringResource(R.string.change_amount_bed)) {
                onDismiss()
                onClickChangeAmount()
            }
            DropMenuText(stringResource(R.string.add_notification)) {
                onDismiss()
                onClickAddNotification()
            }
            DropMenuText(stringResource(R.string.archive_bed)) {
                onDismiss()
                onClickArchive()
            }
            DropMenuText(stringResource(R.string.delete_bed)) {
                onDismiss()
                onClickDelete()
            }

        }
    }
}