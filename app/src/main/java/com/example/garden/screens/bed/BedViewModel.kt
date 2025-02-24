package com.example.garden.screens.bed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garden.models.Bed
import com.example.garden.models.Statistics
import com.example.garden.repository.BedRepository
import com.example.garden.repository.ChangesRepository
import com.example.garden.repository.GalleryRepository
import com.example.garden.repository.StatisticsRepository
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
    private val repoBed: BedRepository,
    private val repoChange: ChangesRepository,
    private val repoGallery: GalleryRepository,
    private val repoStat: StatisticsRepository
) : ViewModel() {
    private val _listBeds = MutableStateFlow<List<Bed>>(emptyList())
    private val _listStat = MutableStateFlow<List<Statistics>>(emptyList())

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
    fun add() = viewModelScope.launch{
        Log.d("TEST","add")
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
    fun addStat() = viewModelScope.launch{
//        Log.d("STAT_TEST","key:"+detail_id)
//        repoStat.addStatistic(
//            Statistics(
//                num = 10,
//                date = Date(2025,10,15),
//                bed_id = _detail_id
//            )
//        )

    }

    fun getStatByBedId(id:String) = viewModelScope.launch(Dispatchers.IO) {
        repoStat.getStatisticByBedId(id).distinctUntilChanged()
            .collect() { list ->
                if (list.isNullOrEmpty()) {
                    Log.d("Error", "empty list")
                }
                _listStat.value = list
            }
    }
}