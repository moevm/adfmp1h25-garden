package com.example.garden.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.garden.models.Bed

@Database(entities = [Bed::class],
    version = 1, exportSchema = false
)
abstract class BedDatabase:RoomDatabase() {
    abstract fun bedDao(): BedDatabaseDao
}