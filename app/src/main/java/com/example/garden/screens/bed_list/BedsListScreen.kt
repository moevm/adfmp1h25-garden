package com.example.garden.screens.bed_list

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
import com.example.garden.screens.widgets.text.ChapterText
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

                    val deleteFunction: () -> Unit = { viewModel.archiveBed(bed) }
                    BedItem(
                        bed = bed,
                        onDeleteClick = deleteFunction,
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
        ChapterText(
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

@Composable
fun BedItem(
    bed: Bed, onDeleteClick: () -> Unit, modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp),
            )
            .background(
                color = Color.White
            )
    ) {
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(percent = 50))
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                null,
                modifier = Modifier
                    .size(15.dp),
                tint = Black
            )
        }

        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Image( // TODO: другая картинка
                        painterResource(R.drawable.image),
                        contentDescription = stringResource(R.string.image),
                        modifier = Modifier
                            .size(96.dp)
                            .fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                Column() {
                    Text(
                        text = bed.title,
                        fontSize = 26.sp
                    )
                    Text(
                        text = bed.sort,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

            }
            Text(
                text = bed.description,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Justify,
                color = Color.Gray
            )
        }
    }
}