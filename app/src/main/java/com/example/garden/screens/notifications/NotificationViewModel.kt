package com.example.garden.screens.notifications

import androidx.lifecycle.ViewModel
import com.example.garden.repository.BedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: BedRepository
) : ViewModel() {

}