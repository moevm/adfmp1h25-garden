package com.example.garden.data.statistics

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.garden.data.Converters
import com.example.garden.data.gallery.GalleryDatabaseDao
import com.example.garden.models.Gallery
import com.example.garden.models.Statistics

@Database(entities = [Statistics::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class StatisticsDatabase: RoomDatabase() {
    abstract fun statisticsDao(): StatisticsDatabaseDao
}