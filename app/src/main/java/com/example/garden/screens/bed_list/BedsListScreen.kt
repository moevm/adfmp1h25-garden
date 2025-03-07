package com.example.garden.screens.bed_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.models.Bed
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination

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
            .padding(50.dp)
    ) {

        val onAddClick: () -> Unit = { navController.navigate(Destination.BedCreating.route) }
        Header(onAddClick = onAddClick)

        if (beds.isEmpty()) {
            // Если грядок нет, выводим сообщение
            Text(
                text = "No beds available. Add a new bed to get started!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Gray
            )
        } else {
            viewModel.listBeds.collectAsState().value.forEach { bed ->

                val deleteFunction: () -> Unit = { viewModel.archiveBed(bed) }
                Column()
                 {
                    BedItem(
                        bed = bed,
                        onDeleteClick= deleteFunction,
                        modifier = Modifier.clickable {
                            //Toast.makeText(context, el.id.toString(), Toast.LENGTH_SHORT).show()
                            //не удалять
                            viewModel.saveBed(bed)
                            viewModel.getStatByBedId(bed.id.toString())
                            viewModel.getGalleryByBedId(bed.id.toString())
                            viewModel.getChangesByBedId(bed.id.toString())
                            navController.navigate(Destination.BedDetail.route)}
                    )
                }
            }
        }
    }
}
@Composable
fun Header( onAddClick: () -> Unit, modifier: Modifier = Modifier){
    Row {
        Text(text = "My beds")
        // Кнопка "Add" (будет стилизована позже)
        Button(
            onClick = onAddClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Add Bed")
        }}
}
@Composable
fun BedItem(
    bed: Bed, onDeleteClick: () -> Unit, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = bed.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Text("delete")
                }
            }
            Text(
                text = "ID: ${bed.id}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}