package com.example.garden.screens

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import com.example.garden.models.Gallery
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
    private val _listGalleryBed = MutableStateFlow<List<Gallery>>(emptyList())
    private val _listChangesBed = MutableStateFlow<List<Changes>>(emptyList())
    private val _notificationsList = MutableStateFlow<List<Notifications>>(emptyList())
    private val _archiveList = MutableStateFlow<List<Bed>>(emptyList())
    private val _date = MutableStateFlow<Date>(Date())
    val archiveList = _archiveList.asStateFlow()
    //private val _bed_id = MutableStateFlow<String>("")
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
    val date get() = _date


    val listBeds = _listBeds.asStateFlow()
    val listStatBed = _listStatBed.asStateFlow()
    val listGalleryBed = _listGalleryBed.asStateFlow()
    val listChangesBed = _listChangesBed.asStateFlow()
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
            repoBed.getBedArchiveList().distinctUntilChanged()
                .collect() { list ->
                    if (list.isNullOrEmpty()) {
                        Log.d("Error", "empty list")
                    }
                    _archiveList.value = list
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

    fun saveDate(date: Date){
        _date.value = date
    }




    fun restoreBed(bed:Bed) = viewModelScope.launch {
        var new_bed = bed
        new_bed.isArchive = false
        repoBed.updateBed(new_bed)
    }

    fun deleteBed(bed:Bed) = viewModelScope.launch {
        getStatByBedId(bed_id = bed.id.toString())
        getGalleryByBedId(bed_id = bed.id.toString())
        getChangesByBedId(bed_id = bed.id.toString())

        listStatBed.value.forEach { stat->
            repoBed.deleteStatistic(stat)
        }
        listGalleryBed.value.forEach { img->
            repoBed.deleteImage(img)
        }
        listChangesBed.value.forEach { change->
            repoBed.deleteChange(change)
        }
        notifications
        repoBed.deleteBed(bed)
    }


    fun addBed(value:Bed) = viewModelScope.launch {

            repoBed.addBed(value)
    }
    fun updateBed(value: Bed) = viewModelScope.launch {
        _bed.value = value
        repoBed.updateBed(value)
    }

    fun getStatByBedId(bed_id: String) = viewModelScope.launch(Dispatchers.IO) {
        //_bed_id.value = bed_id
        repoBed.getStatisticByBedId(bed_id).distinctUntilChanged()
            .collect() { list ->
                if (list.isNullOrEmpty()) {
                    Log.d("Error", "empty list")
                }
                _listStatBed.value = list
            }

    }
    fun getGalleryByBedId(bed_id: String) = viewModelScope.launch(Dispatchers.IO) {
        //_bed_id.value = bed_id
        //Log.d("IMAGE LIST","add list")
        repoBed.getImageByBedId(bed_id).distinctUntilChanged()
            .collect() { list ->
                if (list.isNullOrEmpty()) {
                    Log.d("Error", "empty list")
                }
                _listGalleryBed.value = list
            }

    }
    fun getChangesByBedId(bed_id: String) = viewModelScope.launch(Dispatchers.IO) {
        //_bed_id.value = bed_id
        repoBed.getChangeByBedId(bed_id).distinctUntilChanged()
            .collect() { list ->
                if (list.isNullOrEmpty()) {
                    Log.d("Error", "empty list")
                }
                _listChangesBed.value = list
            }

    }


    fun addStat() = viewModelScope.launch {
        repoBed.addStatistic(
            Statistics(
                date = Date(2020358),
                num = 19,
                bed_id = _bed.value.id.toString()
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
    fun deleteNotification(notifications: Notifications) = viewModelScope.launch {
        repoBed.deleteNotification(notifications)
    }

    fun addImage(
        img:Bitmap,
        bed_id: String
    ) = viewModelScope.launch{
        repoBed.addImage(
            Gallery(
                img = img,
                date = Date(),
                bed_id = bed_id
            )
        )

    }

    fun deleteChange(changes: Changes) = viewModelScope.launch {
        repoBed.deleteChange(changes)
        val c = if(changes.reason_type == R.string.type_reason_present) -1 else 1
        val new_bed = Bed(
            id = _bed.value.id,
            title = _bed.value.title,
            sort = _bed.value.sort,
            isArchive = _bed.value.isArchive,
            amount = _bed.value.amount + changes.amount*c,
            description = _bed.value.description,
            date_sowing =_bed.value.date_sowing
        )
        _bed.value = new_bed
        repoBed.updateBed(
            new_bed
        )
    }

    fun addChanges(
        date:Date,
        type: Int,
        reason:String,
        amount:String
    ) = viewModelScope.launch{
        repoBed.addChange(
            Changes(
                date = date,
                reason = reason,
                amount = amount.toInt(),
                reason_type = type,
                bed_id = bed.value.id.toString()
            )
        )
        val c = if(type == R.string.type_reason_present) 1 else -1
        val new_bed = Bed(
            id = _bed.value.id,
            title = _bed.value.title,
            sort = _bed.value.sort,
            isArchive = _bed.value.isArchive,
            amount = _bed.value.amount + amount.toInt()*c,
            description = _bed.value.description,
            date_sowing =_bed.value.date_sowing
        )
        _bed.value = new_bed
        repoBed.updateBed(
            new_bed
        )
    }


}