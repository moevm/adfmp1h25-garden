package com.example.garden.screens.archive

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject
constructor(

) : ViewModel() {

    private val _archiveList = MutableStateFlow<List<Bed>>(emptyList())
    private val _bed = MutableStateFlow<Bed?>(null)

    private val _showWarning = mutableStateOf(false)
    val showWarning get() = _showWarning
    val archiveList = _archiveList.asStateFlow()
    val bed get() = _bed

    fun changeWarningShow(value:Boolean){
        _showWarning.value = value
    }

    fun saveBed(value:Bed){
        _bed.value = value
    }



}