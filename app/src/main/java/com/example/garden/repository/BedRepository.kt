package com.example.garden.repository


import android.util.Log
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BedRepository @Inject constructor(
    private val bedDao: BedDao,
    private val changeDao: ChangeDao,
    private val galleryDao: GalleryDao,
    private val notificationDao: NotificationDao,
    private val statisticDao: StatisticDao
){
    suspend fun addBed(bed: Bed) = bedDao.insertBed(bed)
    suspend fun deleteBed(bed: Bed) = bedDao.deleteBed(bed)
    suspend fun updateBed(bed: Bed) = bedDao.updateBed(bed)

    fun getBedById(id: String):Flow<Bed?> = bedDao.getBedById(id)


    fun getAllBeds():Flow<List<Bed>> = bedDao.getBedList().flowOn(Dispatchers.IO).conflate()
    fun getBedArchiveList():Flow<List<Bed>> = bedDao.getBedArchiveList().flowOn(Dispatchers.IO).conflate()

    suspend fun addChange(changes: Changes) = changeDao.insertChange(changes)
    suspend fun updateChange(changes: Changes) = changeDao.updateChange(changes)
    suspend fun deleteChange(changes: Changes) = changeDao.deleteChange(changes)
    fun getChangeByBedId(id: String): Flow<List<Changes>> = changeDao.getChangeByBed(id).flowOn(Dispatchers.IO).conflate()
    fun getAllChanges(): Flow<List<Changes>> = changeDao.getAllChanges().flowOn(Dispatchers.IO).conflate()

    suspend fun addImage(gallery: Gallery) = galleryDao.insertImage(gallery)
    suspend fun updateImage(gallery: Gallery) = galleryDao.updateImage(gallery)
    suspend fun deleteImage(gallery: Gallery) = galleryDao.deleteImage(gallery)
    fun getImageByBedId(id: String): Flow<List<Gallery>> = galleryDao.getImagesByBed(id).flowOn(
        Dispatchers.IO).conflate()
    fun getAllImages(): Flow<List<Gallery>> = galleryDao.getAllGallery().flowOn(Dispatchers.IO).conflate()

    suspend fun addStatistic(statistics: Statistics) = statisticDao.insertStat(statistics)
    suspend fun updateStatistic(statistics: Statistics) = statisticDao.updateStat(statistics)
    suspend fun deleteStatistic(statistics: Statistics) = statisticDao.deleteStat(statistics)
    fun getStatisticByBedId(id: String): Flow<List<Statistics>> = statisticDao.getStatByBed(id).flowOn(
        Dispatchers.IO).conflate()
    fun getAllStatistics(): Flow<List<Statistics>> = statisticDao.getAllStat().flowOn(Dispatchers.IO).conflate()

    suspend fun addNotification(notifications: Notifications) = notificationDao.insertNotification(notifications)
    suspend fun updateNotification(notifications: Notifications) = notificationDao.updateNotification(notifications)
    suspend fun deleteNotification(notifications: Notifications) = notificationDao.deleteNotification(notifications)
    fun getAllNotification(): Flow<List<Notifications>> = notificationDao.getAllNotification().flowOn(Dispatchers.IO).conflate()
    fun getNotificationByDate(year:String, month:String):
            Flow<List<Notifications>> = notificationDao.getNotificationByDate(year,month).flowOn(Dispatchers.IO).conflate()

}