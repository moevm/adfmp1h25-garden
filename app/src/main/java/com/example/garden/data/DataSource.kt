package com.example.garden.data

import androidx.compose.ui.res.stringResource
import com.example.garden.R
import com.example.garden.models.Bed
import com.example.garden.models.Notifications
import java.util.Date

class DataSource {
    fun loadNotification():List<Notifications>{
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

    fun loadBed():List<Bed>{
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

    fun getWeek():List<Int>{
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
    fun getMonth():List<Int>{
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
}
