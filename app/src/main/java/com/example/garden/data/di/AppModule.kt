package com.example.garden.data.di

import android.content.Context
import androidx.room.Room
import com.example.garden.data.bed.BedDatabase
import com.example.garden.data.bed.BedDatabaseDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): BedDatabase
            = Room.databaseBuilder(
        context,
        BedDatabase::class.java,
        "garden_db",
    ).fallbackToDestructiveMigrationFrom().build()

//    @Singleton
//    @Provides
//    fun provideAppChangeDatabase(@ApplicationContext context: Context): ChangesDatabase
//            = Room.databaseBuilder(
//        context,
//        ChangesDatabase::class.java,
//        "changes_tbl",
//    ).fallbackToDestructiveMigrationFrom().build()
//
//    @Singleton
//    @Provides
//    fun provideAppGalleryDatabase(@ApplicationContext context: Context): GalleryDatabase
//            = Room.databaseBuilder(
//        context,
//        GalleryDatabase::class.java,
//        "gallery_tbl",
//    ).fallbackToDestructiveMigrationFrom().build()
//
//    @Singleton
//    @Provides
//    fun provideAppStatisticsDatabase(@ApplicationContext context: Context): StatisticsDatabase
//            = Room.databaseBuilder(
//        context,
//        StatisticsDatabase::class.java,
//        "stat_tbl",
//    ).fallbackToDestructiveMigrationFrom().build()
//
//
    @Singleton
    @Provides
    fun provideBedDao(bedDatabase: BedDatabase): BedDatabaseDao
            = bedDatabase.bedDao()
//    @Singleton
//    @Provides
//    fun provideChangeDao(changesDatabase: ChangesDatabase): ChangesDatabaseDao
//            = changesDatabase.changeDao()
//    @Singleton
//    @Provides
//    fun provideGalleryDao(galleryDatabase: GalleryDatabase): GalleryDatabaseDao
//            = galleryDatabase.galleryDao()
//    @Singleton
//    @Provides
//    fun provideStatDao(statisticsDatabase: StatisticsDatabase): StatisticsDatabaseDao
//            = statisticsDatabase.statisticsDao()
}