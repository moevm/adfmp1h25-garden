package com.example.garden.screens.calendar

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.garden.data.DataSource
import com.example.garden.models.Notifications
import com.example.garden.repository.BedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: BedRepository
) : ViewModel(){
    private val _listNotifications = MutableStateFlow<List<Notifications>>(emptyList())
    val listNotification  = _listNotifications.asStateFlow()
    private val _date = MutableStateFlow(Date(2025,3,21))
    private val _listWeek = mutableStateOf<List<Int>>(emptyList())
    private val _listMonth = mutableStateOf<List<Int>>(emptyList())
    private val _listDays = MutableStateFlow<List<Int>>(emptyList())
    private val _year = MutableStateFlow(0)
    private val _month = MutableStateFlow(0)

    val listWeek get()= _listWeek
    val listMonth get() = _listMonth
    val year get() =_year
    val month get() =_month
    init {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.getNotificationByDate(
//                year = "2025",
//                month = "3"
//            ).distinctUntilChanged()
//                .collect() { list ->
//                    if (list.isNullOrEmpty()) {
//                        Log.d("Error", "empty list")
//                    }
//                    _listNotifications.value = list
//                    Log.d("DATETEST", list.toString())
//                }
            repository.getAllNotification().distinctUntilChanged()
                .collect() { list ->
                    if (list.isNullOrEmpty()) {
                        Log.d("Error", "empty list")
                    }
                    _listNotifications.value = list
                    Log.d("DATETEST", list.toString())
                }
        }
    }
    init {
        _listWeek.value = DataSource().getWeek()
        _listMonth.value = DataSource().getMonth()
        _year.value = Calendar.getInstance().get(Calendar.YEAR);
        _month.value = Calendar.getInstance().get(Calendar.MONTH);
    }

    fun getMonth():Int{
        return _listMonth.value[_month.value]
    }
    fun setMonth(index:Int){
        _month.value = index
    }
    fun incYear(){
        _year.value++
    }
    fun decYear(){
        _year.value--
    }

    fun addNotification() = viewModelScope.launch {
        repository.addNotification(
            Notifications(
                dateStart = Date(2025,3,21),
                dateEnd = Date(2025,4,5),
                title = "title",
                description = "desc",
                bed_id = "1"
            )
        )
        repository.addNotification(
            Notifications(
                dateStart = Date(2026,3,21),
                dateEnd = Date(2026,4,5),
                title = "title1",
                description = "desc1",
                bed_id = "1"
            )
        )
    }

    fun changeDate() = viewModelScope.launch {
        _date.value = Date(2026,3,21)
    }




}
