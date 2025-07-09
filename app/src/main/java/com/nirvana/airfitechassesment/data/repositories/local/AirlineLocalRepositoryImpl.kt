package com.nirvana.airfitechassesment.data.repositories.local

import android.util.Log
import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.data.models.AirlineRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AirlineLocalRepositoryImpl @Inject constructor(
    private val realm: Realm
) : AirlineLocalRepository {

    override suspend fun saveAirlines(airlines: List<Airline>) {
        realm.write {
            val oldAirlines = this.query<AirlineRealm>().find()
            delete(oldAirlines) // clear old data
            airlines.forEach {
                copyToRealm(AirlineRealm.from(it))
            }
        }
    }

    override fun getAirlines(): Flow<List<Airline>> = realm.query<AirlineRealm>()
        .asFlow()
        .map { results -> results.list.map { it.toDomain() } }

    override suspend fun getAirlineById(id: String): Airline? {
        return realm.query<AirlineRealm>("id == $0", id).first().find()?.toDomain()
    }

    override suspend fun logData() {
        Log.d("RealmCheck", "logData: ")
        val results = realm .query<AirlineRealm>().find()
        results.forEach {
            Log.d("RealmCheck", "Stored Airline: ${it.id})")
        }
    }
}
