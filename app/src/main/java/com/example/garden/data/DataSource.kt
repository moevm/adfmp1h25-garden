package com.example.garden.data

import androidx.compose.ui.graphics.Color
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.models.Notifications
import com.example.garden.ui.theme.IconLightGreen
import com.example.garden.ui.theme.IconLightRed
import com.example.garden.ui.theme.IconLightYellow
import java.util.Date

class DataSource {
    fun loadNotification(): List<Notifications> {
        return listOf(
            Notifications(
                dateStart = Date(0),
                dateEnd = Date(100),
                title = "T1",
                bed_id = "1",
                description = ""
            ),
            Notifications(
                dateStart = Date(0),
                dateEnd = Date(100),
                title = "T2",
                bed_id = "1",
                description = ""
            ),
        )
    }

    fun loadBed(): List<Bed> {
        return listOf(
            Bed(
                title = "Bed1",
                description = "alsdkjfladf",
                sort = "sort11",
                amount = 10,
                date_sowing = Date(0)
            ),
            Bed(
                title = "Bed2",
                description = "some info",
                sort = "sort2",
                amount = 100,
                date_sowing = Date(40)
            )
        )
    }

    fun loadLegend(): List<Pair<Int, Color>> {
        return listOf(
            Pair(R.string.good_day, IconLightGreen),
            Pair(R.string.middle_day, IconLightYellow),
            Pair(R.string.bad_day, IconLightRed)
        )
    }

    fun getWeek(): List<Int> {
        return listOf(
            R.string.monday,
            R.string.tuesday,
            R.string.wednesday,
            R.string.thursday,
            R.string.friday,
            R.string.saturday,
            R.string.sunday
        )
    }

    fun getMonth(): List<Int> {
        return listOf(
            R.string.january,
            R.string.february,
            R.string.march,
            R.string.april,
            R.string.may,
            R.string.june,
            R.string.jule,
            R.string.august,
            R.string.september,
            R.string.october,
            R.string.november,
            R.string.december
        )
    }

    fun getReasons(): List<Int> {
        return listOf(
            R.string.type_reason_frost,
            R.string.type_reason_inspects,
            R.string.type_reason_present
        )
    }
}
