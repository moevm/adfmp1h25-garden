package com.example.garden.models

import androidx.room.Entity
import androidx.room.ForeignKey
import java.util.Date
import java.util.UUID

@Entity(
    tableName = "changes_tbl",
    foreignKeys = [ForeignKey(
        entity = Bed::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("bed_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Changes(
    val id: UUID = UUID.randomUUID(),
    val date: Date,
    val amount:Int,
    val reason: String,
    val bed_id:UUID
)