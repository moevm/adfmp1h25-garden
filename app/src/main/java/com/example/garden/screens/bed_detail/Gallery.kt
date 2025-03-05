package com.example.garden.screens.bed_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.garden.R
import com.example.garden.models.Gallery
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.ui.theme.Black
import com.example.garden.ui.theme.White

@Composable
fun Gallery(listGallery: List<Gallery>, onAddClick: () -> Unit) {
    var galleryShow by remember {
        mutableStateOf(false)
    }
    var galleryArrow by remember {
        mutableStateOf(0f)
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ChapterText(stringResource(R.string.gallery))
        IconButton(
            onClick = {
                galleryShow = !galleryShow
                galleryArrow = (galleryArrow + 180f) % 360f
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .rotate(galleryArrow)
            )
        }
    }

    if (galleryShow)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(15.dp),

            ) {
            listGallery.forEach { img ->
                Box(
                    modifier = Modifier
                        .size(75.dp)
                        .clip(RoundedCornerShape(20))
                        .rotate(90f)
                ) {
                    Image(
                        bitmap = img.img.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Button(
                onClick = onAddClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = White
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.add_image),
                    contentDescription = "",
                    tint = Black
                )
            }
        }


}