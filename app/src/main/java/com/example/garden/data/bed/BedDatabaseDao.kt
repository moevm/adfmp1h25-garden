package com.example.garden.data.bed

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import com.example.garden.models.Gallery
import com.example.garden.models.Statistics
import kotlinx.coroutines.flow.Flow

@Dao
interface BedDatabaseDao {
    @Query("SELECT * FROM bed_list_tbl")
    fun getBedList(): Flow<List<Bed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertBed(bed: Bed)

    @Delete()
    suspend fun  deleteBed(bed: Bed)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBed(bed: Bed)

    @Query("SELECT * FROM bed_list_tbl WHERE id == :id")
    suspend fun getBedById(id:String):Bed


    ///////////////////////////////////

    @Query("SELECT * FROM changes_tbl")
    fun getAllChanges(): Flow<List<Changes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChange(changes: Changes)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateChange(changes: Changes)

    @Delete()
    suspend fun deleteChange(changes: Changes)

    @Query("SELECT * FROM changes_tbl WHERE bed_id == :id")
    fun getChangeByBed(id:String): Flow<List<Changes>>

    ////////////////////////////////////////
    @Query("SELECT * FROM gallery_tbl")
    fun getAllGallery(): Flow<List<Gallery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(gallery: Gallery)

    @Delete()
    suspend fun  deleteImage(gallery: Gallery)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateImage(gallery: Gallery)

    @Query("SELECT * FROM gallery_tbl WHERE bed_id == :id")
    fun getImagesByBed(id:String):Flow<List<Gallery>>
    /////////////////////////////////////////////////////
    @Query("SELECT * FROM stat_tbl")
    fun getAllStat(): Flow<List<Statistics>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStat(statistics: Statistics)

    @Delete()
    suspend fun deleteStat(statistics: Statistics)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStat(statistics: Statistics)

    @Query("SELECT * FROM stat_tbl WHERE bed_id == :id")
    fun getStatByBed(id:String): Flow<List<Statistics>>
}