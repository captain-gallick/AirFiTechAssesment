package com.nirvana.airfitechassesment.data.network

import com.nirvana.airfitechassesment.data.models.Airline
import retrofit2.http.GET

interface AirlineApi {
    @GET("airlines")
    suspend fun getAirlines(): List<Airline>
}