package com.example.garden.screens.bed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garden.data.DataSource
import com.example.garden.models.Bed
import com.example.garden.models.Notifications
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BedListViewModel @Inject constructor():ViewModel() {
    private val _bedList = MutableStateFlow<List<Bed>>(emptyList())
    val bedList = _bedList.asStateFlow()
    init {
        viewModelScope.launch {
            _bedList.value = DataSource().loadBed()
        }
    }
}