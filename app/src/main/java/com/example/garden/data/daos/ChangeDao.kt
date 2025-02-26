package com.example.garden.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.garden.models.Changes
import kotlinx.coroutines.flow.Flow

@Dao
interface ChangeDao {
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
}