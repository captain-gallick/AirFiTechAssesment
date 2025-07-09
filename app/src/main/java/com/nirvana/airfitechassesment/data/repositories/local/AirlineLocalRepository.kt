package com.nirvana.airfitechassesment.data.repositories.local

import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.data.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AirlineLocalRepository {
    suspend fun saveAirlines(airlines: List<Airline>)
    fun getAirlines(): Flow<List<Airline>>
    suspend fun getAirlineById(id: String): Airline?
    suspend fun logData()
}
