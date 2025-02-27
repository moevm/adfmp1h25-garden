package com.example.garden.screens.archive

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garden.data.DataSource
import com.example.garden.models.Bed
import com.example.garden.models.Notifications
import com.example.garden.repository.BedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject
constructor(
    private val repository: BedRepository
) : ViewModel() {

    private val _archiveList = MutableStateFlow<List<Bed>>(emptyList())
    val archiveList = _archiveList.asStateFlow()

    init {
//        viewModelScope.launch {
//            _notificationsList.value = DataSource().loadNotification()
//        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBedArchiveList().distinctUntilChanged()
                .collect() { list ->
                    if (list.isNullOrEmpty()) {
                        Log.d("Error", "empty list")
                    }
                    _archiveList.value = list
                    Log.d("DATETEST", list.toString())
                }
        }
    }

    fun delete(bed:Bed) = viewModelScope.launch {
        repository.deleteBed(bed)
    }

    fun update(bed:Bed) = viewModelScope.launch {
        var new_bed = bed
        new_bed.isArchive = false
        repository.updateBed(new_bed)
    }
}