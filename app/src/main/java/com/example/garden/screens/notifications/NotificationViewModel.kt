package com.example.garden.screens.notifications

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(

) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val search = _searchText.asStateFlow()

    fun searchChange(value: String) {
        _searchText.value = value
    }

    fun compare(title: String): Boolean {
        return true
    }
}