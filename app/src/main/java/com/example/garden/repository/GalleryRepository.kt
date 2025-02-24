package com.example.garden.repository

import com.example.garden.data.changes.ChangesDatabaseDao
import com.example.garden.data.gallery.GalleryDatabaseDao
import com.example.garden.models.Changes
import com.example.garden.models.Gallery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val galleryDatabaseDao: GalleryDatabaseDao
) {
    suspend fun addImage(gallery: Gallery) = galleryDatabaseDao.insertImage(gallery)
    suspend fun updateImage(gallery: Gallery) = galleryDatabaseDao.updateImage(gallery)
    suspend fun deleteImage(gallery: Gallery) = galleryDatabaseDao.deleteImage(gallery)
    fun getImageByBedId(id: String): Flow<List<Gallery>> = galleryDatabaseDao.getImagesByBed(id).flowOn(
        Dispatchers.IO).conflate()
    fun getAllImages(): Flow<List<Gallery>> = galleryDatabaseDao.getAllGallery().flowOn(Dispatchers.IO).conflate()
}