package com.example.garden.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.Date
import java.util.UUID


@Entity(
    tableName = "stat_tbl",
//    foreignKeys = [androidx.room.ForeignKey(
//        entity = Bed::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("bed_id"),
//        onDelete = androidx.room.ForeignKey.CASCADE
//    )]
)
data class Statistics (
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val date: Date,
    val num: Int,
    val bed_id: String
)

class BedWithStat {
    @Embedded
    var bed: Bed? = null

    @Relation(parentColumn = "id", entity = Statistics::class, entityColumn = "bed_id")
    var stats: List<Statistics>? = null //other fields
}