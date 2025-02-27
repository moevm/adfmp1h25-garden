package com.example.garden.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.garden.models.Statistics
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticDao {
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