package com.example.garden.data.daos

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.garden.models.Notifications
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification_tbl")
    fun getAllNotification(): Flow<List<Notifications>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notifications: Notifications)

    @Delete()
    suspend fun deleteNotification(notifications: Notifications)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNotification(notifications: Notifications)

    @Query("SELECT * FROM notification_tbl WHERE strftime('%Y', date(dateStart)) = :year AND strftime('%m', date(dateStart)) = :month " +
           "OR strftime('%Y', dateEnd) = :year AND strftime('%m', dateEnd) = :month")
    fun getNotificationByDate(year:String, month:String): Flow<List<Notifications>>
}