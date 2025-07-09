package com.nirvana.airfitechassesment.data.repositories

import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.data.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AirlineRepository {
    fun getAirlines(): Flow<NetworkResult<List<Airline>>>
    suspend fun getAirlineById(id: String): Airline?
    suspend fun logData()
    suspend fun saveAirlines(airlines: List<Airline>)
}
