package com.example.garden.data.bed

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.garden.data.Converters
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import com.example.garden.models.Gallery
import com.example.garden.models.Notifications
import com.example.garden.models.Statistics

@Database(
    entities = [
        Bed::class,
        Changes::class,
        Gallery::class,
        Statistics::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BedDatabase : RoomDatabase() {
    abstract fun bedDao(): BedDatabaseDao
}