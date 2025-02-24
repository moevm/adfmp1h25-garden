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
        "notes_db",
    ).fallbackToDestructiveMigrationFrom().build()

    @Singleton
    @Provides
    fun provideNotesDao(bedDatabase: BedDatabase): BedDatabaseDao
            = bedDatabase.bedDao()
}