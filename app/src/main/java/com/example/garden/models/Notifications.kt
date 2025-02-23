package com.example.garden.models

import java.util.Date

data class Notifications(
    val id: Int,
    val dateStart: Date,
    val dateEnd: Date,
    val title: String,
    val bed_id: Int,
    val description: String
)