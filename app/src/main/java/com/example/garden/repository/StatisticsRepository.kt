package com.example.garden.repository

import com.example.garden.data.gallery.GalleryDatabaseDao
import com.example.garden.data.statistics.StatisticsDatabaseDao
import com.example.garden.models.Gallery
import com.example.garden.models.Statistics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class StatisticsRepository @Inject constructor(
    private val statisticsDatabaseDao: StatisticsDatabaseDao
) {
    suspend fun addStatistic(statistics: Statistics) = statisticsDatabaseDao.insertStat(statistics)
    suspend fun updateStatistic(statistics: Statistics) = statisticsDatabaseDao.updateStat(statistics)
    suspend fun deleteStatistic(statistics: Statistics) = statisticsDatabaseDao.deleteStat(statistics)
    fun getStatisticByBedId(id: String): Flow<List<Statistics>> = statisticsDatabaseDao.getStatByBed(id).flowOn(
        Dispatchers.IO).conflate()
    fun getAllStatistics(): Flow<List<Statistics>> = statisticsDatabaseDao.getAllStat().flowOn(Dispatchers.IO).conflate()
}