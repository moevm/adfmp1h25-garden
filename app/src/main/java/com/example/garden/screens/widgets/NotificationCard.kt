package com.example.garden.screens.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garden.R
import com.example.garden.models.Notifications
import com.example.garden.screens.widgets.text.BigCardText
import com.example.garden.screens.widgets.text.MediumCardText
import com.example.garden.screens.widgets.text.SmallCardText
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun NotificationCard(
    title: String,
    dateStart: Date,
    dateEnd: Date,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: is it ok?????
    val sdf = SimpleDateFormat("dd.MM.yyyy")



    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
    ) {

        Icon(
            imageVector = Icons.Default.Close,
            null,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .size(30.dp)
                .wrapContentWidth(Alignment.End)
                .clickable { onDeleteClick() }
        )


        Column(
            modifier = Modifier
                .padding(12.dp)

        ) {
            BigCardText(
                text = title
            )
            Row() {
                Column() {
                    MediumCardText(
                        text = stringResource(R.string.start),
                    )
                    MediumCardText(
                        text = stringResource(R.string.end)
                    )
                }
                Spacer(modifier = Modifier.width(2.dp))
                Column() {
                    SmallCardText(
                        text = sdf.format(dateStart)
                    )
                    SmallCardText(
                        text = sdf.format(dateEnd)
                    )
                }
            }
        }
    }
}
