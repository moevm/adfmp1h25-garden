package com.example.garden.screens.widgets

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.screens.widgets.text.BigCardText
import com.example.garden.screens.widgets.text.MediumCardText
import com.example.garden.screens.widgets.text.SmallCardText
import com.example.garden.ui.theme.Black

@Composable
fun BedItem(
    bed: Bed,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,

) {
    Box(
        modifier = modifier
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
                    .size(30.dp),
                tint = Black
            )
        }



            Row(verticalAlignment = Alignment.Top,modifier = Modifier
                .padding(12.dp)) {
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

                Column(
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    BigCardText(
                        text = bed.title,
                    )
                    MediumCardText(
                        text = bed.sort
                    )
                }

            }
//            SmallCardText(
//                text = bed.description,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Medium,
//                textAlign = TextAlign.Justify,
//                color = Color.Gray
//            )

    }
}