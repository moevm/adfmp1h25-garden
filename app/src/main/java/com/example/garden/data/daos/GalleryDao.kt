package com.example.garden.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.garden.models.Gallery
import kotlinx.coroutines.flow.Flow

@Dao
interface GalleryDao {
    @Query("SELECT * FROM gallery_tbl")
    fun getAllGallery(): Flow<List<Gallery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(gallery: Gallery)

    @Delete()
    suspend fun deleteImage(gallery: Gallery)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateImage(gallery: Gallery)

    @Query("SELECT * FROM gallery_tbl WHERE bed_id == :id")
    fun getImagesByBed(id: String): Flow<List<Gallery>>
}