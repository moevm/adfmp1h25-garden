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

    private val _showWarningResolve = mutableStateOf(false)
    private val _showWarningDelete = mutableStateOf(false)
    val showWarningResolve get() = _showWarningResolve
    val showWarningDelete get() = _showWarningDelete
    val archiveList = _archiveList.asStateFlow()
    val bed get() = _bed

    fun changeWarningResolveShow(value:Boolean){
        _showWarningResolve.value = value
    }

    fun changeWarningDeleteShow(value:Boolean){
        _showWarningDelete.value = value
    }

    fun saveBed(value:Bed){
        _bed.value = value
    }



}