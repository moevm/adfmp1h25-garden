package com.example.garden.screens.calendar

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garden.data.DataSource
import com.example.garden.models.Day
import com.example.garden.models.Notifications
import com.example.garden.repository.BedRepository
import com.example.garden.ui.theme.IconLightGreen
import com.example.garden.ui.theme.IconLightRed
import com.example.garden.ui.theme.IconLightYellow
import com.example.garden.ui.theme.White
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: BedRepository
) : ViewModel() {
    private val _listNotifications = MutableStateFlow<List<Notifications>>(emptyList())
    val listNotification = _listNotifications.asStateFlow()
    private val _date = MutableStateFlow(Date(2025, 3, 21))
    private val _listWeek = mutableStateOf<List<Int>>(emptyList())
    private val _listMonth = mutableStateOf<List<Int>>(emptyList())
    private val _listLegend = mutableStateOf<List<Pair<Int, Color>>>(emptyList())
    private val _listDays = MutableStateFlow<List<Day>>(emptyList())
    private val _year = MutableStateFlow(0)
    private val _month = MutableStateFlow(0)
    private val _start_day = MutableStateFlow(0)


    val listWeek get() = _listWeek
    val listMonth get() = _listMonth
    val year get() = _year
    val month get() = _month
    val start_day get() = _start_day
    val listDays get() = _listDays
    val legend get() = _listLegend

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
                    if (list.isEmpty()) {
                        Log.d("Error", "empty list")
                    }
                    _listNotifications.value = list
                    setListDays()
                    //Log.d("DATETEST", list.toString())
                }
        }
    }

    init {
        _listLegend.value = DataSource().loadLegend()
        val calendar = Calendar.getInstance()
        _listWeek.value = DataSource().getWeek()
        _listMonth.value = DataSource().getMonth()
        _year.value = calendar.get(Calendar.YEAR);
        _month.value = calendar.get(Calendar.MONTH);

    }


    private fun getMoonStage(day: Int): Color {

        if (day <= 0) return Color.Transparent

        val phase =
            if (_month.value == 0) ((_year.value - 1) * 367 + 13 * 28 + day) % 30
            else if (_month.value == 1) ((_year.value - 1) * 367 + 14 * 28 + day) % 30
            else (_year.value * 367 + (_month.value + 1) * 28 + day) % 30
        when {
            phase <= 15 -> return IconLightGreen
            phase <= 22 -> return IconLightYellow
            phase <= 29 -> return IconLightRed
            else -> return White
        }
    }

    private fun setListDays() {
        val calendar = Calendar.getInstance()
        calendar.set(_year.value, _month.value, 1)
        _start_day.value = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7

        _listDays.value =
            ((1 - _start_day.value)..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).map { day ->
                Day(
                    day = day,
                    color = getMoonStage(day),
                    notification = checkDay(day)
                )
            }
    }

    fun getDate(day: Day): Date {
        val calendar = Calendar.getInstance()
        calendar.set(_year.value, _month.value, day.day)
        return Date(calendar.timeInMillis)
    }

    fun getMonth(): Int {
        return _listMonth.value[_month.value]
    }

    fun setMonth(index: Int) {
        _month.value = index
        setListDays()
    }

    fun incYear(): Boolean {
        if (_year.value > getCurrentYear() + 5) {
            return false
        }
        _year.value++
        setListDays()
        return true
    }

    fun decYear(): Boolean {
        if (_year.value < getCurrentYear() - 1) {
            return false
        }
        _year.value--
        setListDays()
        return true
    }

    private fun getCurrentYear(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.YEAR)
    }

    fun changeDate() = viewModelScope.launch {
        _date.value = Date(2026, 3, 21)
    }

    fun checkDay(day: Int): Boolean {
        if (day <= 0) return false

        val sdf_year = SimpleDateFormat("yyyy")
        val sdf_month = SimpleDateFormat("MM")
        val sdf_day = SimpleDateFormat("dd")
        val calendar = Calendar.getInstance()
        calendar.set(_year.value, _month.value, day)
        val date = Date(calendar.timeInMillis)

        _listNotifications.value.forEach {
            if (sdf_year.format(it.dateStart).toInt() <= sdf_year.format(date).toInt() &&
                sdf_year.format(date).toInt() <= sdf_year.format(it.dateEnd).toInt() &&
                sdf_month.format(it.dateStart).toInt() <= sdf_month.format(date).toInt() &&
                sdf_month.format(date).toInt() <= sdf_month.format(it.dateEnd).toInt() &&
                sdf_day.format(it.dateStart).toInt() <= sdf_day.format(date).toInt() &&
                sdf_day.format(date).toInt() <= sdf_day.format(it.dateEnd).toInt()
            )
                return true
        }
        return false
    }
}
