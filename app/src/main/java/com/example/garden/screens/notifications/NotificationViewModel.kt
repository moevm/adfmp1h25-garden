package com.example.garden.screens.notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.garden.data.DataSource
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
class NotificationViewModel @Inject constructor(
    private val repository: BedRepository
):ViewModel() {
    private val _notificationsList = MutableStateFlow<List<Notifications>>(emptyList())
    val notifications = _notificationsList.asStateFlow()
    init {
        viewModelScope.launch {
            _notificationsList.value = DataSource().loadNotification()
        }
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getAllNotification().distinctUntilChanged()
//                .collect() { list ->
//                    if (list.isNullOrEmpty()) {
//                        Log.d("Error", "empty list")
//                    }
//                    _notificationsList.value = list
//                    Log.d("DATETEST", list.toString())
//                }
//        }
    }
}