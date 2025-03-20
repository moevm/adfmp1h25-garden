package com.example.garden.screens.bed_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.garden.R
import com.example.garden.models.Changes
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White
import java.text.SimpleDateFormat


@Composable
fun Statistics(listChanges: List<Changes>, max: Int, min: Int) {
    var statShow by remember {
        mutableStateOf(false)
    }
    var statArrow by remember {
        mutableStateOf(0f)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ChapterText(stringResource(R.string.statistics))
        IconButton(
            onClick = {
                statShow = !statShow
                statArrow = (statArrow + 180f) % 360f
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .rotate(statArrow)
            )
        }
    }
    val height = (max + min).toFloat()
    if (statShow)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listChanges.forEach {
                ChartBar(it, height, max, min)
            }

        }
}

@Composable
fun ChartBar(changes: Changes, height: Float, max: Int, min: Int) {
    val sdf = SimpleDateFormat("dd.MM")
    if (changes.reason_type == R.string.type_reason_present) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(200.dp)
        ) {
            if (max - changes.amount != 0) Box(
                modifier = Modifier
                    .weight((max - changes.amount) / height)
                    .width(
                        30.dp,
                    )
                    .background(White),
            )
            Box(
                modifier = Modifier
                    .weight(changes.amount / height)
                    .width(
                        30.dp,
                    )
                    .background(LightGreen),
                contentAlignment = Alignment.Center
            ) {
                Text(changes.amount.toString(), color = White)
            }
            Text(sdf.format(changes.date))
            Box(
                modifier = Modifier
                    .weight((height - min) / height)
                    .width(
                        30.dp,
                    )
                    .background(White),
            )
        }
    } else {
        Column(
            modifier = Modifier.height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .weight((height - max) / height)
                    .width(
                        30.dp,
                    )
                    .background(White),
            )
            Text(sdf.format(changes.date))
            Box(
                modifier = Modifier
                    .weight(changes.amount / height)
                    .width(
                        30.dp,
                    )
                    .background(LightGreen),
                contentAlignment = Alignment.Center
            ) {
                Text(changes.amount.toString(), color = White)
            }
            if (min - changes.amount != 0)
                Box(
                    modifier = Modifier
                        .weight((min - changes.amount) / height)
                        .width(
                            30.dp,
                        )
                        .background(White),
                )

        }
    }
}