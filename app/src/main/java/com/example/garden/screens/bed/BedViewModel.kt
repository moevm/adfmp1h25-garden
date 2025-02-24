package com.example.garden.screens.bed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garden.models.Bed
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
    private val repo: BedRepository
) : ViewModel() {
    private val _listBeds = MutableStateFlow<List<Bed>>(emptyList())
    val listBeds = _listBeds.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllBeds().distinctUntilChanged()
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
        repo.addBed(
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