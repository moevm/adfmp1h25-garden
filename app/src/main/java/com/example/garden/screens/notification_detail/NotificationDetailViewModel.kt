package com.example.garden.screens.notification_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.garden.models.Bed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NotificationDetailViewModel @Inject constructor():ViewModel() {
    private val _title = mutableStateOf("")
    private val _dateStart = mutableStateOf("")
    private val _dateEnd = mutableStateOf("")
    private val _desc = mutableStateOf("")
    private val _bed = mutableStateOf<Bed?>(null)

    val title get() = _title
    val dateStart get() = _dateStart
    val dateEnd get() = _dateEnd
    val desc get() = _desc
    val bed get() = _bed

    fun changeTitle(value:String){
        _title.value = value
    }
    fun changeDateStart(value:String){
        _dateStart.value = value
    }
    fun changeDateEnd(value:String){
        _dateEnd.value = value
    }
    fun changeDesc(value:String){
        _desc.value = value
    }
    fun changeBed(bed:Bed){
        _bed.value = bed
    }
}