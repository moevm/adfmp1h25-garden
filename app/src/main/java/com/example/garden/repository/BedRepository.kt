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

    fun getAllBeds():Flow<List<Bed>> = bedDatabaseDao.getBedList().flowOn(Dispatchers.IO).conflate()
}