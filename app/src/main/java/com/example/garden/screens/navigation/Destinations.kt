package com.example.garden.screens.navigation

import kotlinx.serialization.Serializable

sealed class Destinations{
    @Serializable
    object Calendar
    @Serializable
    object AllPlants
    @Serializable
    object AllNotifications
    @Serializable
    object Archive
    @Serializable
    object Plant
    @Serializable
    object Notification
    @Serializable
    object EditPlant
    @Serializable
    object AddPlant
    @Serializable
    object Date
}