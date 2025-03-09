package com.example.garden.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.garden.R
import kotlinx.serialization.Serializable

sealed class Destination(val route:String){
    data object Calendar : Destination("Calendar")
    data object BedsList : Destination("BedsList")
    data object Notifications : Destination("Notifications")
    data object Archive : Destination("Archive")
    data object BedDetail : Destination("BedDetail")
    data object NotificationDetail : Destination("NotificationDetail")
    data object BedEdit : Destination("BedEdit")
    data object BedCreating : Destination("BedCreating")
    data object NotificationDate : Destination("NotificationDate")
    data object Home : Destination("Home")
    data object About : Destination("About")
}

sealed class BottomDestination(val destination: Destination, val icon: Int){
    data object Calendar:BottomDestination(Destination.Calendar, R.drawable.calendar)
    data object BedsList:BottomDestination(Destination.BedsList, R.drawable.plant)
    data object Notification:BottomDestination(Destination.Notifications, R.drawable.notification)
    data object Archive:BottomDestination(Destination.Archive, R.drawable.archiive)
}
val bottomNav = listOf<BottomDestination>(
    BottomDestination.Calendar,
    BottomDestination.BedsList,
    BottomDestination.Notification,
    BottomDestination.Archive
)
