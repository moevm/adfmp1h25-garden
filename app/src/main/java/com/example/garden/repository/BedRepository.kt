package com.example.garden.repository


import com.example.garden.data.bed.BedDatabaseDao
import com.example.garden.models.Bed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BedRepository @Inject constructor(
    private val bedDatabaseDao: BedDatabaseDao
){
    suspend fun addBed(bed: Bed) = bedDatabaseDao.insertBed(bed)
    suspend fun deleteBed(bed: Bed) = bedDatabaseDao.deleteBed(bed)
    suspend fun updateBed(bed: Bed) = bedDatabaseDao.updateBed(bed)
    suspend fun getBedById(id: String):Bed = bedDatabaseDao.getBedById(id)
    fun getAllBeds():Flow<List<Bed>> = bedDatabaseDao.getBedList().flowOn(Dispatchers.IO).conflate()
}