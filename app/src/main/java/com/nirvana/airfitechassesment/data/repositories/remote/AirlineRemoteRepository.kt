package com.nirvana.airfitechassesment.data.repositories.remote

import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.data.network.NetworkResult

interface AirlineRemoteRepository {
    suspend fun fetchAirlines(): NetworkResult<List<Airline>>
}