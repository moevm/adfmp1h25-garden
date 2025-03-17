package com.example.garden.screens.archive

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.garden.models.Bed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    fun changeWarningShow(value: Boolean) {
        _showWarning.value = value
    }

    fun saveBed(value: Bed) {
        _bed.value = value
    }


}