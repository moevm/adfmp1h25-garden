package com.example.garden.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.garden.models.Bed
import kotlinx.coroutines.flow.Flow

@Dao
interface BedDatabaseDao {
    @Query("SELECT * FROM bed_list_tbl")
    fun getBedList(): Flow<List<Bed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertBed(bed: Bed)
}