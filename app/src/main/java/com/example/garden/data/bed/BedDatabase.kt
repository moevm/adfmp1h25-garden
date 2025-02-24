package com.example.garden.data.bed

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.garden.data.Converters
import com.example.garden.models.Bed

@Database(entities = [Bed::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BedDatabase:RoomDatabase() {
    abstract fun bedDao(): BedDatabaseDao
}