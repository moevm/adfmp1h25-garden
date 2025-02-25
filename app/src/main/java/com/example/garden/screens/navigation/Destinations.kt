package com.example.garden.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
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
}

sealed class BottomDestination(val destination: Destination, val icon: ImageVector){
    data object Calendar:BottomDestination(Destination.Calendar, Icons.Default.Home)
    data object BedsList:BottomDestination(Destination.BedsList, Icons.Default.Add)
    data object Notification:BottomDestination(Destination.Notifications, Icons.Default.Call)
    data object Archive:BottomDestination(Destination.Archive, Icons.Default.Notifications)
}
val bottomNav = listOf<BottomDestination>(
    BottomDestination.Calendar,
    BottomDestination.BedsList,
    BottomDestination.Notification,
    BottomDestination.Archive
)
