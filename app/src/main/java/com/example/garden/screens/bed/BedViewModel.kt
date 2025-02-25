package com.example.garden.screens.bed

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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
    private val repoBed: BedRepository
) : ViewModel() {
    private val _listBeds = MutableStateFlow<List<Bed>>(emptyList())
    val listBeds = _listBeds.asStateFlow()

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

}