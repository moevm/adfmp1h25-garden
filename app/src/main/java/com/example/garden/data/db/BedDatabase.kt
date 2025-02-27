package com.example.garden.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.garden.data.Converters
import com.example.garden.data.daos.BedDao
import com.example.garden.data.daos.ChangeDao
import com.example.garden.data.daos.GalleryDao
import com.example.garden.data.daos.NotificationDao
import com.example.garden.data.daos.StatisticDao
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
        Statistics::class,
        Notifications::class
    ],
    version = 3, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BedDatabase : RoomDatabase() {
    abstract fun bedDao(): BedDao
    abstract fun changeDao(): ChangeDao
    abstract fun galleryDao(): GalleryDao
    abstract fun notificationDao(): NotificationDao
    abstract fun statisticDao(): StatisticDao
}