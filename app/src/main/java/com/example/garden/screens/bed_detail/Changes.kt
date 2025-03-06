package com.example.garden.screens.bed_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garden.R
import com.example.garden.models.Changes
import com.example.garden.models.Gallery
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.ui.theme.Black
import com.example.garden.ui.theme.Gray
import com.example.garden.ui.theme.White
import java.text.SimpleDateFormat

@Composable
fun Changes(
    listChanges: List<Changes>,
    onDeleteClick: (Changes) -> Unit
) {
    var changesShow by remember {
        mutableStateOf(false)
    }
    var changesArrow by remember {
        mutableStateOf(0f)
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ChapterText(stringResource(R.string.changes))
        IconButton(
            onClick = {
                changesShow = !changesShow
                changesArrow = (changesArrow + 180f) % 360f
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .rotate(changesArrow)
            )
        }
    }

    if (changesShow)
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(bottom = 50.dp)
                .verticalScroll(rememberScrollState())
            //.horizontalScroll(rememberScrollState())

            ,
            verticalArrangement = Arrangement.spacedBy(15.dp),

            ) {
            listChanges.forEach { change ->
                ChangeCard(change, onDeleteClick)
            }

        }
}

@Composable
fun ChangeCard(changes: Changes, onDeleteClick: (Changes) -> Unit) {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    Box(modifier = Modifier.fillMaxWidth()
        .shadow(elevation = 10.dp, spotColor = Gray,shape = RoundedCornerShape(8.dp))
        .background(White).padding(bottom = 20.dp)) {
        IconButton(
            onClick = {onDeleteClick(changes)},
            modifier = Modifier.size(40.dp).align(Alignment.TopEnd)
        ) {

            Icon(imageVector = Icons.Default.Close, contentDescription = "")
        }
        Column (modifier = Modifier.fillMaxWidth().padding(20.dp)){
            Text(
                text = sdf.format(changes.date),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Row {
                Text(
                    text =
                    if (changes.reason_type == R.string.type_reason_present)
                        stringResource(R.string.new_plant) + ": "
                    else
                        stringResource(R.string.killed_plant) + ": ",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Text(
                    text = changes.amount.toString(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.reason) + ": ",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Text(
                    text = changes.reason,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
        }
    }
}