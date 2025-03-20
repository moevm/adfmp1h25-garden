package com.example.garden.screens.bed_detail

import androidx.lifecycle.ViewModel
import com.example.garden.R
import com.example.garden.models.Changes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BedDetailViewModel @Inject constructor() : ViewModel() {
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

    fun changeImageShow(imageShow: Boolean) {
        _alertImageShow.value = imageShow
    }

    fun changeAddNotificationShow(addNotificationShow: Boolean) {
        _alertAddNotificationShow.value = addNotificationShow
    }

    fun changeShowDropDown(showDropDown: Boolean) {
        _showDropDown.value = showDropDown
    }

    fun changeShowAddChanges(alertShowAddChanges: Boolean) {
        _alertShowAddChanges.value = alertShowAddChanges
    }

    fun getMax(list: List<Changes>): Int {
        var max = 0
        list.forEach {
            if (max < it.amount && it.reason_type == R.string.type_reason_present) max = it.amount
        }
        return max
    }

    fun getMin(list: List<Changes>): Int {
        var min = 0
        list.forEach {
            if (min < it.amount && it.reason_type != R.string.type_reason_present) min = it.amount
        }
        return min
    }
}


