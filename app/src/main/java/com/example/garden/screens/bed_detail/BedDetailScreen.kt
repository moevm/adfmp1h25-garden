package com.example.garden.screens.bed_detail

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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.AddNotificationAlertDialog
import com.example.garden.screens.widgets.ImageAlertDialog
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.screens.widgets.text.ContentText
import com.example.garden.screens.widgets.text.DropMenuText
import com.example.garden.screens.widgets.text.TitleText
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White
import java.text.SimpleDateFormat

@Composable
fun BedDetailScreen(navController: NavHostController, dbViewModel: DBViewModel) {
    val bed by dbViewModel.bed.collectAsState()
    var alertImageShow by remember {
        mutableStateOf(false)
    }
    var alertAddNotificationShow by remember {
        mutableStateOf(false)
    }
    var showDropDown by remember {
        mutableStateOf(false)
    }
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

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Box() {
                    IconButton(
                        onClick = {
                            showDropDown = true
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
                            showDropDown = false
                        },
                        onClickAddNotification = {
                            alertAddNotificationShow = true
                        },
                        onClickArchive = {
                            dbViewModel.archiveBed(bed)
                            navController.navigate(Destination.Home.route)
                        },
                        onClickChangeAmount = {

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
                listGallery = dbViewModel.listGalleryBed.collectAsState().value,
                onAddClick = { alertImageShow = true },

                )


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ChapterText(stringResource(R.string.statistics))
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ChapterText(stringResource(R.string.changes))
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

        }
    }

    if(alertAddNotificationShow)
        AddNotificationAlertDialog(
            onDismissRequest = {
                alertAddNotificationShow = false
            },
            onConfirmation = dbViewModel::addNotification,
            listBeds = dbViewModel.listBeds.collectAsState().value,
            currentBed = bed
        )
    if (alertImageShow)
        ImageAlertDialog(
            onDismiss = {
                alertImageShow = false
            },
            onConfirmation = {
                dbViewModel.addImage(it, bed.id.toString())
                alertImageShow = false
            }
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