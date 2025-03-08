package com.example.garden.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(
    tableName = "changes_tbl",
//    foreignKeys = [ForeignKey(
//        entity = Bed::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("bed_id"),
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class Changes(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val tmp_id:Int =0,
    val date: Date,
    val amount: Int,
    val reason: String,
    val reason_type:Int,
    val bed_id: String
)