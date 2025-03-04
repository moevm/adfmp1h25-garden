package com.example.garden.screens.bed_detail

import android.graphics.Bitmap
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.models.Gallery
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.CameraView
import com.example.garden.screens.widgets.ImageAlertDialog
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.screens.widgets.text.TitleText
import com.example.garden.ui.theme.Black
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White

@Composable
fun BedDetailScreen(navController: NavHostController, dbViewModel: DBViewModel) {
    val bed by dbViewModel.bed.collectAsState()
    var alertShow by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(White)) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)){
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

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End) {
                IconButton(
                    onClick = {},
                ) {
                    Icon(imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = DarkGreen,
                        modifier = Modifier.size(35.dp))
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
                .padding(horizontal = 20.dp),

            ) {
            Gallery(
                listGallery = dbViewModel.listGalleryBed.collectAsState().value,
                onAddClick = {alertShow = true},

            )


            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
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
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
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
        Button(onClick = {
            navController.navigate(Destination.BedEdit.route)
        }) {
            Text("edit")
        }
//        Button(onClick = {
//            viewModel.addStat()
//        }) {
//
//        }
        Text("info")


//        Text("stat")
//        viewModel.listStatBed.collectAsState().value.forEach { el->
//            Text(text = el.bed_id)
//        }
    }
    if(alertShow)
        ImageAlertDialog(
            onDismiss = {
                alertShow=false
            },
            onConfirmation = {
                dbViewModel.addImage(it,bed.id.toString())
                alertShow = false
            }
        )

}

@Composable
fun Gallery(listGallery:List<Gallery>,onAddClick:()->Unit) {
    var galleryShow by remember {
        mutableStateOf(false)
    }
    var galleryArrow by remember {
        mutableStateOf(0f)
    }


    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        ChapterText(stringResource(R.string.gallery))
        IconButton(
            onClick = {
                galleryShow = !galleryShow
                galleryArrow= (galleryArrow+180f)%360f
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(35.dp).rotate(galleryArrow)
            )
        }
    }

    if(galleryShow)
        Row(modifier = Modifier.fillMaxWidth()) {
            listGallery.forEach { img ->
                Box(modifier = Modifier.size(75.dp)){
                    Image(bitmap = img.img.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop)
                }
            }
            Button(onClick = onAddClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = White
                )) {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.add_image),
                    contentDescription = "",
                    tint = Black
                )
            }
        }



}