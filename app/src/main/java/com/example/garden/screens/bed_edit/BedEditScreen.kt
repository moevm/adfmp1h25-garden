package com.example.garden.screens.bed_edit

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination
import com.example.garden.screens.widgets.BottomButton
import com.example.garden.screens.widgets.WarningAlertDialog
import com.example.garden.screens.widgets.convertMillisToDate
import com.example.garden.screens.widgets.text.ContentTextField
import com.example.garden.screens.widgets.text.DataTextField
import com.example.garden.screens.widgets.text.TitleTextField
import com.example.garden.ui.theme.LightGreen
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BedEditScreen(
    navController: NavHostController,
    dbViewModel: DBViewModel,
    bedEditViewModel: BedEditViewModel = hiltViewModel()
) {
    val showWarning = bedEditViewModel.showWarningAlert.collectAsState().value

    val title = bedEditViewModel.bedTitle.value
    val desc = bedEditViewModel.desc.value
    val amount = bedEditViewModel.amount.value
    val sort = bedEditViewModel.sort.value
    val date = bedEditViewModel.sowingDate.value
    val datePicker = rememberDatePickerState()
    bedEditViewModel.changeDate(datePicker.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: "")
    val bed = dbViewModel.bed.collectAsState().value
    LaunchedEffect(bed) {
        bedEditViewModel.changeTitle(bed.title)
        bedEditViewModel.changeDesc(bed.description)
        bedEditViewModel.changeSort(bed.sort)
        bedEditViewModel.changeAmount(bed.amount.toString())
        datePicker.selectedDateMillis = bed.date_sowing.time
//        bedEditViewModel.changeDate(bed.date_sowing.time.let {
//            convertMillisToDate(it)
//        } )
    }
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
                bedEditViewModel.changeShowWarningAlert(true)
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

            TitleTextField(
                value = title,
                onChange = { bedEditViewModel.changeTitle(it) },
                label = stringResource(
                    R.string.alert_add_event_bed
                )
            )
            ContentTextField(
                value = sort,
                onChange = { bedEditViewModel.changeSort(it) },
                label = stringResource(
                    R.string.sort
                )
            )
            ContentTextField(
                value = amount,
                onChange = { bedEditViewModel.changeAmount(it) },
                label = stringResource(
                    R.string.amount
                ),
                isNumber = true
            )
            DataTextField(value = date, label = stringResource(R.string.sowing_date), datePicker)
            ContentTextField(
                value = desc,
                onChange = { bedEditViewModel.changeDesc(it) },
                label = stringResource(
                    R.string.description
                )
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp, start = 20.dp, end = 20.dp)
    ) {
        BottomButton(
            text = stringResource(R.string.alert_save),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            if (title.isNotEmpty() && sort.isNotEmpty() && amount.isNotEmpty() &&
                amount.isDigitsOnly() && desc.isNotEmpty() && date.isNotEmpty()
            ) {
                try {
                    var new_bed =
                        Bed(
                            id = bed.id,
                            title = title,
                            description = desc,
                            date_sowing = datePicker.selectedDateMillis?.let { Date(it) } ?: Date(),
                            amount = amount.toInt(),
                            sort = sort,
                            isArchive = bed.isArchive
                        )
                    dbViewModel.updateBed(new_bed)
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
                    context.getString(R.string.bed_successfully_updated), Toast.LENGTH_SHORT
                ).show()
                navController.navigate(Destination.BedDetail.route)
            } else {
                Toast.makeText(context, toast_mes, Toast.LENGTH_SHORT).show()
            }

        }
    }

    if (showWarning)
        WarningAlertDialog(
            title = stringResource(R.string.alert_title_dismiss_edit),
            onDismiss = {
                bedEditViewModel.changeShowWarningAlert(false)
            },
            onConfirm = {
                bedEditViewModel.changeShowWarningAlert(false)
                navController.navigate(Destination.BedDetail.route)
            }
        )
}