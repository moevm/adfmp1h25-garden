package com.example.garden.models

import java.util.Date

data class Changes(
    val id: Int,
    val date: Date,
    val amount:Int,
    val reason: String
)