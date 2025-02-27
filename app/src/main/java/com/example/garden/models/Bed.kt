package com.example.garden.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "bed_list_tbl")
data class Bed(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val tmp_id:Int =0,
    val title: String,
    val description: String,
    val sort: String,
    val amount:Int,
    val date_sowing:Date,
    var isArchive:Boolean = false
)