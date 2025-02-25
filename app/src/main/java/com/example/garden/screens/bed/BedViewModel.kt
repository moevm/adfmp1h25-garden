package com.example.garden.screens.bed

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.garden.models.Bed
import com.example.garden.models.Statistics
import com.example.garden.repository.BedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BedViewModel @Inject constructor(
    private val repoBed: BedRepository
) : ViewModel() {
    private val _listBeds = MutableStateFlow<List<Bed>>(emptyList())
    private val _listStat = MutableStateFlow<List<Statistics>>(emptyList())

    private val _bed_id = MutableStateFlow<String>("")

    val listBeds = _listBeds.asStateFlow()
    val listStat = _listStat.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repoBed.getAllBeds().distinctUntilChanged()
                .collect() { list ->
                    if (list.isNullOrEmpty()) {
                        Log.d("Error", "empty list")
                    }
                    _listBeds.value = list
                }

        }
    }
    fun getBedById(){

    }
    fun add() = viewModelScope.launch{
        repoBed.addBed(
            Bed(
                title = "Title",
                description = "desc",
                sort = "sort",
                amount = 10,
                date_sowing = Date(2025,10,15)
            )
        )
    }

    fun getStatByBedId(bed_id:String) = viewModelScope.launch(Dispatchers.IO) {
        _bed_id.value = bed_id
        repoBed.getStatisticByBedId(bed_id).distinctUntilChanged()
            .collect() { list ->
                if (list.isNullOrEmpty()) {
                    Log.d("Error", "empty list")
                }
                _listStat.value = list
            }

    }

    fun addStat()
      =  viewModelScope.launch {
        repoBed.addStatistic(
            Statistics(
                date = Date(2020358),
                num = 19,
                bed_id = _bed_id.value
            )

        )
    }


}