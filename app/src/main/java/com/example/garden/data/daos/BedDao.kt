package com.example.garden.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import com.example.garden.models.Gallery
import com.example.garden.models.Notifications
import com.example.garden.models.Statistics
import kotlinx.coroutines.flow.Flow

@Dao
interface BedDao {
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

}