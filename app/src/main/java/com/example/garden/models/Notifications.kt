package com.example.garden.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID


@Entity(
    tableName = "notification_tbl"
)
data class Notifications(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val tmp_id:Int =0,
    val dateStart: Date,
    val dateEnd: Date,
    val title: String,
    val bed_id: String,
    val description: String
)