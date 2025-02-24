package com.example.garden.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(
    tableName = "stat_tbl",
    foreignKeys = [ForeignKey(
        entity = Bed::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("bed_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Statistics (
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val date: Date,
    val num: Int,
    val bed_id: String
)