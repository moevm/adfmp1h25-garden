package com.example.garden.screens.archive

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.garden.models.Bed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject
constructor(

) : ViewModel() {

    private val _archiveListThisYear = MutableStateFlow<List<Bed>>(emptyList())
    private val _archiveListPrevYear = MutableStateFlow<List<Bed>>(emptyList())
    private val _archiveListOther = MutableStateFlow<List<Bed>>(emptyList())
    private val _bed = MutableStateFlow<Bed?>(null)
    private val _year = mutableStateOf(0)

    private val _showWarningResolve = mutableStateOf(false)
    private val _showWarningDelete = mutableStateOf(false)
    val showWarningResolve get() = _showWarningResolve
    val showWarningDelete get() = _showWarningDelete
    val archiveListThisYear = _archiveListThisYear.asStateFlow()
    val archiveListPrevYear = _archiveListPrevYear.asStateFlow()
    val archiveListOtherYear = _archiveListOther.asStateFlow()
    val bed get() = _bed

    init {
        _year.value = Calendar.getInstance().get(Calendar.YEAR)
    }
    fun filter(list: List<Bed>){
        val sdf_year = SimpleDateFormat("yyyy")
        _archiveListThisYear.value = list.filter {
            sdf_year.format(it.date_sowing).toInt() == _year.value
        }
        _archiveListPrevYear.value = list.filter {
            sdf_year.format(it.date_sowing).toInt() == _year.value - 1
        }
        _archiveListOther.value = list.filter {
            sdf_year.format(it.date_sowing).toInt() != _year.value &&
                    sdf_year.format(it.date_sowing).toInt() != _year.value - 1
        }
    }
    fun changeWarningResolveShow(value:Boolean){
        _showWarningResolve.value = value
    }

    fun changeWarningDeleteShow(value:Boolean){
        _showWarningDelete.value = value
    }

    fun saveBed(value: Bed) {
        _bed.value = value
    }
}