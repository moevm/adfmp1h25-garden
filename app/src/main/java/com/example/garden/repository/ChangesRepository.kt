package com.example.garden.repository

import com.example.garden.data.changes.ChangesDatabaseDao
import com.example.garden.models.Bed
import com.example.garden.models.Changes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChangesRepository @Inject constructor(
    private val changesDatabaseDao: ChangesDatabaseDao
) {
    suspend fun addChange(changes: Changes) = changesDatabaseDao.insertChange(changes)
    suspend fun updateChange(changes: Changes) = changesDatabaseDao.updateChange(changes)
    suspend fun deleteChange(changes: Changes) = changesDatabaseDao.deleteChange(changes)
    fun getChangeByBedId(id: String): Flow<List<Changes>> = changesDatabaseDao.getChangeByBed(id).flowOn(Dispatchers.IO).conflate()
    fun getAllChanges(): Flow<List<Changes>> = changesDatabaseDao.getAllChanges().flowOn(Dispatchers.IO).conflate()

}