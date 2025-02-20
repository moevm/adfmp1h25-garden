package com.example.garden.screens.navigation

import kotlinx.serialization.Serializable

sealed class Destination(val route:String){
    object Calendar : Destination("Calendar")
    object BedsList : Destination("BedsList")
    object Notifications : Destination("Notifications")
    object Archive : Destination("Archive")
    object BedDetail : Destination("BedDetail")
    object NotificationDetail : Destination("NotificationDetail")
    object BedEdit : Destination("BedEdit")
    object BedCreating : Destination("BedCreating")
    object NotificationDate : Destination("NotificationDate")
}