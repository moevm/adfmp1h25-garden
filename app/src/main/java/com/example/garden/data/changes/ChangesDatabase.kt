package com.example.garden.data.changes

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.garden.data.Converters
import com.example.garden.data.bed.BedDatabaseDao
import com.example.garden.models.Bed
import com.example.garden.models.Changes

@Database(entities = [Changes::class, Bed::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ChangesDatabase: RoomDatabase() {
    abstract fun changeDao(): ChangesDatabaseDao
}
