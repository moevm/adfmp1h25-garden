package com.example.garden.screens.bed_edit

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BedEditViewModel @Inject constructor():ViewModel() {
    private val _showWarningAlert = MutableStateFlow(false)

    private val _bedTitle = mutableStateOf("")
    private val _sort = mutableStateOf("")
    private val _desc = mutableStateOf("")
    private val _amount = mutableStateOf("")
    private val _sowingDate = mutableStateOf("")


    val showWarningAlert get() = _showWarningAlert
    val bedTitle get() = _bedTitle
    val sort get() = _sort
    val desc get() = _desc
    val amount get() = _amount
    val sowingDate get() = _sowingDate

    fun changeShowWarningAlert(value:Boolean){
        _showWarningAlert.value = value
    }

    fun changeTitle(value:String){
        _bedTitle.value = value
    }
    fun changeSort(value:String){
        _sort.value = value
    }
    fun changeDesc(value:String){
        _desc.value = value
    }
    fun changeAmount(value:String){
        _amount.value = value
    }
    fun changeDate(value:String){
        _sowingDate.value = value
    }
}