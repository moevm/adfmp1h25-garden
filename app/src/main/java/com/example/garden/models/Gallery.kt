package com.example.garden.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(
    tableName = "gallery_tbl",
//    foreignKeys = [ForeignKey(
//        entity = Bed::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("bed_id"),
//        onDelete = ForeignKey.CASCADE
//    )]
    )
data class Gallery(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val img: Int,
    val date: Date,
    val bed_id: String
)