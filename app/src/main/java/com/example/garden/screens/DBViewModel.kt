package com.example.garden.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import com.example.garden.models.Notifications
import com.example.garden.models.Statistics
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
class DBViewModel @Inject constructor(
    private val repoBed: BedRepository
) : ViewModel() {
    private val _listBeds = MutableStateFlow<List<Bed>>(emptyList())
    private val _listStatBed = MutableStateFlow<List<Statistics>>(emptyList())
    private val _notificationsList = MutableStateFlow<List<Notifications>>(emptyList())

    private val _bed_id = MutableStateFlow<String>("")
    private val _bed = MutableStateFlow(
        Bed(
            title = "none",
            description = "",
            sort = "",
            amount = 10,
            date_sowing = Date(0)
        )
    )
    private val _note = MutableStateFlow(
        Notifications(
            dateStart = Date(0),
            dateEnd = Date(100),
            title = "T1",
            bed_id = "1",
            description = ""
        )
    )

    val bed get() = _bed
    val note get() = _note


    val listBeds = _listBeds.asStateFlow()
    val listStatBed = _listStatBed.asStateFlow()
    val notifications = _notificationsList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repoBed.getAllBeds().distinctUntilChanged()
                .collect() { list ->
                    if (list.isNullOrEmpty()) {
                        Log.d("Error", "empty list")
                    }
                    _listBeds.value = list
                }

        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repoBed.getAllNotification().distinctUntilChanged()
                .collect() { list ->
                    if (list.isNullOrEmpty()) {
                        Log.d("Error", "empty list")
                    }
                    _notificationsList.value = list
                    //Log.d("DATETEST", list.toString())
                }
        }
    }

    fun saveBed(bed_new: Bed) = viewModelScope.launch() {
        _bed.value = bed_new
    }

    fun saveNote(note_new:Notifications)=viewModelScope.launch() {
        _note.value = note_new
    }

    fun archiveBed(bed:Bed) = viewModelScope.launch {
        var new_bed = bed
        new_bed.isArchive = true
        repoBed.updateBed(new_bed)
    }



    fun add() = viewModelScope.launch {

            repoBed.addBed(
                Bed(
                    title = "Title",
                    description = "desc",
                    sort = "sort",
                    amount = 10,
                    date_sowing = Date(2025, 10, 15)
                )
            )
    }

    fun getStatByBedId(bed_id: String) = viewModelScope.launch(Dispatchers.IO) {
        _bed_id.value = bed_id
        repoBed.getStatisticByBedId(bed_id).distinctUntilChanged()
            .collect() { list ->
                if (list.isNullOrEmpty()) {
                    Log.d("Error", "empty list")
                }
                _listStatBed.value = list
            }

    }

    fun addStat() = viewModelScope.launch {
        repoBed.addStatistic(
            Statistics(
                date = Date(2020358),
                num = 19,
                bed_id = _bed_id.value
            )

        )
    }

    fun addNotification(
        title:String,
        bed_id:String,
        description:String,
        dateStart: Date,
        dateEnd: Date,
    ) = viewModelScope.launch {
        repoBed.addNotification(
            Notifications(
                dateStart = dateStart,
                dateEnd = dateEnd,
                title = title,
                description = description,
                bed_id = bed_id
            )
        )
    }


}