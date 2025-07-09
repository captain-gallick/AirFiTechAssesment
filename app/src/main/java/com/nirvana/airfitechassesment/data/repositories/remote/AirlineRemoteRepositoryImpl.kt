package com.nirvana.airfitechassesment.data.repositories.remote

import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.data.network.AirlineApi
import com.nirvana.airfitechassesment.data.network.NetworkResult
import javax.inject.Inject

class AirlineRemoteRepositoryImpl @Inject constructor(
    private val api: AirlineApi
) : AirlineRemoteRepository {
    override suspend fun fetchAirlines(): NetworkResult<List<Airline>> {
        return try {
            val response = api.getAirlines()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e.localizedMessage ?: "Unknown error")
        }
    }
}
