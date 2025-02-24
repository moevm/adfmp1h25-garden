package com.example.garden.data.gallery

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.garden.data.Converters
import com.example.garden.data.changes.ChangesDatabaseDao
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import com.example.garden.models.Gallery

@Database(entities = [Gallery::class, Bed::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GalleryDatabase: RoomDatabase() {
    abstract fun galleryDao(): GalleryDatabaseDao
}