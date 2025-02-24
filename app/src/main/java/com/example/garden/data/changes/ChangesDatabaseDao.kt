package com.example.garden.data.changes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import com.example.garden.models.Statistics
import kotlinx.coroutines.flow.Flow

@Dao
interface ChangesDatabaseDao {
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