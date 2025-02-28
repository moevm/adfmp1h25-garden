package com.example.garden.screens.calendar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.garden.data.DataSource
import com.example.garden.models.Notifications
import com.example.garden.repository.BedRepository
import com.example.garden.ui.theme.IconLightGreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.LocalDate
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
    private val _listDays = MutableStateFlow<List<Pair<Int,Color>>>(emptyList())
    private val _year = MutableStateFlow(0)
    private val _month = MutableStateFlow(0)
    private val _start_day = MutableStateFlow(0)

    val listWeek get()= _listWeek
    val listMonth get() = _listMonth
    val year get() =_year
    val month get() =_month
    val start_day get() = _start_day
    val listDays get() = _listDays
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
        val calendar = Calendar.getInstance()
        _listWeek.value = DataSource().getWeek()
        _listMonth.value = DataSource().getMonth()
        _year.value = calendar.get(Calendar.YEAR);
        _month.value = calendar.get(Calendar.MONTH);
        setListDays()
    }
    private fun getMoonStage(day:Int):Color{
        if(day<=0) return Color.Transparent
        return IconLightGreen
    }

    private fun setListDays(){
        val calendar = Calendar.getInstance()
        calendar.set(_year.value, _month.value, 1)
        _start_day.value =  (calendar.get(Calendar.DAY_OF_WEEK)+5)%7

        _listDays.value = ((1 - _start_day.value)..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).map { day ->
            val color = getMoonStage(day)
            day to color
        }

    }

    fun getMonth():Int{
        return _listMonth.value[_month.value]
    }

    fun setMonth(index:Int){
        _month.value = index
        setListDays()
    }
    fun incYear(){
        _year.value++
        setListDays()
    }
    fun decYear(){
        _year.value--
        setListDays()
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
