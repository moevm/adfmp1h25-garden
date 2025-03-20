package com.example.garden.screens.bed_creating

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.BottomButton
import com.example.garden.screens.widgets.ImageAlertDialog
import com.example.garden.screens.widgets.WarningAlertDialog
import com.example.garden.screens.widgets.convertMillisToDate
import com.example.garden.screens.widgets.text.ContentTextField
import com.example.garden.screens.widgets.text.DataTextField
import com.example.garden.screens.widgets.text.TitleTextField
import com.example.garden.ui.theme.LightGreen
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BedCreatingScreen(
    navController: NavHostController,
    dbViewModel: DBViewModel,
    bedCreatingViewModel: BedCreatingViewModel = hiltViewModel()
) {
    val showImagePicker = bedCreatingViewModel.showImageAlert.collectAsState().value
    val showWarning = bedCreatingViewModel.showWarningAlert.collectAsState().value
    val img = bedCreatingViewModel.image.collectAsState().value
    val title = bedCreatingViewModel.bedTitle.value
    val desc = bedCreatingViewModel.desc.value
    val amount = bedCreatingViewModel.amount.value
    val sort = bedCreatingViewModel.sort.value
    val date = bedCreatingViewModel.sowingDate.value
    val datePicker = rememberDatePickerState()
    bedCreatingViewModel.changeDate(datePicker.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: "")
    val context = LocalContext.current
    val toast_mes = stringResource(R.string.check_data)
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 50.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                bedCreatingViewModel.changeShowWarningAlert(true)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                    contentDescription = null
                )
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
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20))
                    .size(150.dp)

                    .clickable {
                        bedCreatingViewModel.changeShowImageAlert(true)
                    }) {
                if (img == null)
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                else
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
            }

            Spacer(modifier = Modifier.height(20.dp))
            TitleTextField(
                value = title,
                onChange = { bedCreatingViewModel.changeTitle(it) },
                label = stringResource(R.string.alert_add_event_bed)
            )
            ContentTextField(
                value = sort,
                onChange = { bedCreatingViewModel.changeSort(it) },
                label = stringResource(R.string.sort)
            )
            ContentTextField(
                value = amount,
                onChange = { bedCreatingViewModel.changeAmount(it) },
                label = stringResource(R.string.amount),
                isNumber = true
            )
            DataTextField(value = date, label = stringResource(R.string.sowing_date), datePicker)
            ContentTextField(
                value = desc,
                onChange = { bedCreatingViewModel.changeDesc(it) },
                label = stringResource(R.string.description),
                imeAction = ImeAction.Done
            )
        }


    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp, start = 20.dp, end = 20.dp)
    ) {
        BottomButton(
            text = stringResource(R.string.add),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            if (title.isNotEmpty() && sort.isNotEmpty() &&
                date.isNotEmpty()
            ) {
                try {
                    val bed = Bed(
                        title = title,
                        description = desc,
                        date_sowing = datePicker.selectedDateMillis?.let { Date(it) } ?: Date(),
                        amount = getAmount(amount),
                        sort = sort,
                        img = img
                    )
                    dbViewModel.addBed(bed)

                    if (img != null)
                        dbViewModel.addImage(img, bed.id.toString())
                } catch (exception: NumberFormatException) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.amount_too_much), Toast.LENGTH_SHORT
                    ).show()
                    return@BottomButton
                } catch (exception: IllegalArgumentException) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.amount_must_be_a_number), Toast.LENGTH_SHORT
                    ).show()
                    return@BottomButton
                } catch (exception: Exception) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.unexpected_error), Toast.LENGTH_SHORT
                    ).show()
                    return@BottomButton
                }

                Toast.makeText(
                    context,
                    context.getString(R.string.bed_successfully_created), Toast.LENGTH_SHORT
                ).show()

                navController.navigate(Destination.Home.route)
            } else {
                Toast.makeText(context, toast_mes, Toast.LENGTH_SHORT).show()
            }
        }
    }
    if (showImagePicker)
        ImageAlertDialog(
            onDismiss = {
                bedCreatingViewModel.changeShowImageAlert(false)
            },
            onConfirmation = {
                bedCreatingViewModel.saveImage(it)
                bedCreatingViewModel.changeShowImageAlert(false)
            }
        )
    if (showWarning)
        WarningAlertDialog(
            title = stringResource(R.string.alert_title_dismiss_create),
            onDismiss = {
                bedCreatingViewModel.changeShowWarningAlert(false)
            },
            onConfirm = {
                bedCreatingViewModel.changeShowWarningAlert(false)
                navController.navigate(Destination.Home.route)
            }
        )

}

fun getAmount(amount: String): Int {
    if (!amount.isDigitsOnly()) {
        throw IllegalArgumentException()
    }
    return if (amount.isEmpty()) {
        0
    } else {
        amount.toInt()
    }
}

@Preview
@Composable
private fun Prev() {
    val nav = rememberNavController()
    val bd: DBViewModel = hiltViewModel()
    BedCreatingScreen(nav, bd)
}