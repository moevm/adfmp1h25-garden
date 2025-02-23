package com.example.garden.models

import java.util.Date

data class Bed(
    val id: Int,
    val title: String,
    val description: String,
    val sort: String,
    val amount:Int,
    val date_sowing:Date
)