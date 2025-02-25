package com.example.garden.repository


import com.example.garden.data.bed.BedDatabaseDao
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import com.example.garden.models.Gallery
import com.example.garden.models.Statistics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BedRepository @Inject constructor(
    private val bedDatabaseDao: BedDatabaseDao
){
    suspend fun addBed(bed: Bed) = bedDatabaseDao.insertBed(bed)
    suspend fun deleteBed(bed: Bed) = bedDatabaseDao.deleteBed(bed)
    suspend fun updateBed(bed: Bed) = bedDatabaseDao.updateBed(bed)
    suspend fun getBedById(id: String):Bed = bedDatabaseDao.getBedById(id)
    fun getAllBeds():Flow<List<Bed>> = bedDatabaseDao.getBedList().flowOn(Dispatchers.IO).conflate()

    suspend fun addChange(changes: Changes) = bedDatabaseDao.insertChange(changes)
    suspend fun updateChange(changes: Changes) = bedDatabaseDao.updateChange(changes)
    suspend fun deleteChange(changes: Changes) = bedDatabaseDao.deleteChange(changes)
    fun getChangeByBedId(id: String): Flow<List<Changes>> = bedDatabaseDao.getChangeByBed(id).flowOn(Dispatchers.IO).conflate()
    fun getAllChanges(): Flow<List<Changes>> = bedDatabaseDao.getAllChanges().flowOn(Dispatchers.IO).conflate()

    suspend fun addImage(gallery: Gallery) = bedDatabaseDao.insertImage(gallery)
    suspend fun updateImage(gallery: Gallery) = bedDatabaseDao.updateImage(gallery)
    suspend fun deleteImage(gallery: Gallery) = bedDatabaseDao.deleteImage(gallery)
    fun getImageByBedId(id: String): Flow<List<Gallery>> = bedDatabaseDao.getImagesByBed(id).flowOn(
        Dispatchers.IO).conflate()
    fun getAllImages(): Flow<List<Gallery>> = bedDatabaseDao.getAllGallery().flowOn(Dispatchers.IO).conflate()

    suspend fun addStatistic(statistics: Statistics) = bedDatabaseDao.insertStat(statistics)
    suspend fun updateStatistic(statistics: Statistics) = bedDatabaseDao.updateStat(statistics)
    suspend fun deleteStatistic(statistics: Statistics) = bedDatabaseDao.deleteStat(statistics)
    fun getStatisticByBedId(id: String): Flow<List<Statistics>> = bedDatabaseDao.getStatByBed(id).flowOn(
        Dispatchers.IO).conflate()
    fun getAllStatistics(): Flow<List<Statistics>> = bedDatabaseDao.getAllStat().flowOn(Dispatchers.IO).conflate()
}