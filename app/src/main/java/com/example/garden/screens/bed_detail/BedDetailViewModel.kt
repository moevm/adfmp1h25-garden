package com.example.garden.screens.bed_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BedDetailViewModel @Inject constructor():ViewModel() {
    //переменные для показа всплывающих элементов
    private val _alertImageShow = MutableStateFlow(false)
    private val _alertAddNotificationShow = MutableStateFlow(false)
    private val _showDropDown = MutableStateFlow(false)
    private val _alertShowAddChanges = MutableStateFlow(false)

    //переменные для работы в функции
    val alertImageShow get() = _alertImageShow
    val alertAddNotificationShow get() = _alertAddNotificationShow
    val showDropDown get() = _showDropDown
    val alertShowAddChanges get() = _alertShowAddChanges

    fun changeImageShow(imageShow:Boolean){
        _alertImageShow.value =  imageShow
    }

    fun changeAddNotificationShow(addNotificationShow:Boolean){
        _alertAddNotificationShow.value =  addNotificationShow
    }

    fun changeShowDropDown(showDropDown:Boolean){
        _showDropDown.value =  showDropDown
    }

    fun changeShowAddChanges(alertShowAddChanges:Boolean){
        _alertShowAddChanges.value =  alertShowAddChanges
    }
}


