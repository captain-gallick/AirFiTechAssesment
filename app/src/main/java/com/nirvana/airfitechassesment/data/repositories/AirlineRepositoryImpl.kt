package com.nirvana.airfitechassesment.data.repositories

import android.util.Log
import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.data.models.AirlineRealm
import com.nirvana.airfitechassesment.data.network.NetworkResult
import com.nirvana.airfitechassesment.data.repositories.local.AirlineLocalRepository
import com.nirvana.airfitechassesment.data.repositories.remote.AirlineRemoteRepository
import com.nirvana.airfitechassesment.utils.JsonUtils
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirlineRepositoryImpl @Inject constructor(
    private val remoteRepository: AirlineRemoteRepository,
    private val localRepository: AirlineLocalRepository,
    private val jsonUtils: JsonUtils
) : AirlineRepository {

    override fun getAirlines(): Flow<NetworkResult<List<Airline>>> = flow {
        emit(NetworkResult.Loading)

        // Emit cached data first
        val cached = localRepository.getAirlines().firstOrNull()
        if (!cached.isNullOrEmpty()) {
            emit(NetworkResult.Success(cached))
        }

        // Try fetching fresh data
        when (val result = remoteRepository.fetchAirlines()) {
            is NetworkResult.Success -> {
                val localJson = jsonUtils.loadAirlinesFromAssets()
                localRepository.saveAirlines(localJson)
                emit(NetworkResult.Success(localJson)) // 👈 override with local
            }
            is NetworkResult.Error -> {
                if (!cached.isNullOrEmpty()) {
                    emit(NetworkResult.Success(cached))
                } else {
                    emit(NetworkResult.Error(result.message))
                }
                //emit(NetworkResult.Error(result.message))
            }
            else -> Unit
        }
    }

    override suspend fun getAirlineById(id: String): Airline? {
        return localRepository.getAirlineById(id)
    }

    override suspend fun logData() {
        localRepository.logData()
    }

    override suspend fun saveAirlines(airlines: List<Airline>) {
        localRepository.saveAirlines(airlines)
    }
}
