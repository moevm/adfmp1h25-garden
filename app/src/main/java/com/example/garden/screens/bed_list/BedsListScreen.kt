package com.example.garden.screens.bed_list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.ArchiveAlertDialog
import com.example.garden.screens.widgets.BedItem
import com.example.garden.screens.widgets.WarningAlertDialog
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.screens.widgets.text.TitleText
import com.example.garden.ui.theme.Black
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White

@Composable
fun BedsListScreen(
    navController: NavHostController,
    viewModel: DBViewModel,
    bedListViewModel: BedListViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var bedToArchive: Bed? by remember { mutableStateOf<Bed?>(null) }

    val beds = viewModel.listBeds.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(bottom = 96.dp)
    ) {
        val onAddClick: () -> Unit = { navController.navigate(Destination.BedCreating.route) }
        Header(onAddClick = onAddClick)

        if (beds.isEmpty()) {
            // Если грядок нет, выводим сообщение
            Text(
                text = stringResource(R.string.no_beds_available),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Gray
            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        16.dp
                    )
            )
            {
                viewModel.listBeds.collectAsState().value.forEach { bed ->

                    val deleteFunction: () -> Unit = {

                        viewModel.archiveBed(bed)
                        Toast.makeText(
                            context,
                            bed.title + context.getString(R.string.has_been_moved_to_the_archive),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    BedItem(
                        bed = bed,
                        onDeleteClick = { bedToArchive = bed },
                        modifier = Modifier.clickable {
                            //Toast.makeText(context, el.id.toString(), Toast.LENGTH_SHORT).show()
                            //не удалять
                            viewModel.saveBed(bed)
                            viewModel.getStatByBedId(bed.id.toString())
                            viewModel.getGalleryByBedId(bed.id.toString())
                            viewModel.getChangesByBedId(bed.id.toString())
                            navController.navigate(Destination.BedDetail.route)
                        }
                    )
                }
            }
        }
    }

    // INFO: saving to local variable to manage thread-safe assertion
    val selectedBedToArchive = bedToArchive
    if (selectedBedToArchive != null) {
        WarningAlertDialog(
            onConfirm = {
                viewModel.archiveBed(selectedBedToArchive)
                Toast.makeText(
                    context,
                    selectedBedToArchive.title + context.getString(R.string.has_been_moved_to_the_archive),
                    Toast.LENGTH_SHORT
                ).show()
                bedToArchive = null
            },
            onDismiss = {
                bedToArchive = null
            },
            title = stringResource(R.string.are_you_sure_you_want_to_archive_bed) + " " + selectedBedToArchive.title + "?"
        )
    }
}

@Composable
fun Header(onAddClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 46.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        TitleText(
            text = stringResource(R.string.my_beds)
        )
        IconButton(
            onClick = onAddClick,
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .clip(RoundedCornerShape(percent = 50))
                .background(LightGreen)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                null,
                modifier = Modifier
                    .size(30.dp),
                tint = White
            )
        }
    }
    HorizontalDivider(
        color = LightGreen,
        thickness = 2.dp,
        modifier = Modifier
            .padding(
                top = 24.dp
            )
    )
}

@Preview
@Composable
fun Prew(modifier: Modifier = Modifier) {
    val onAddClick: () -> Unit = { }
    Header(onAddClick)
}

