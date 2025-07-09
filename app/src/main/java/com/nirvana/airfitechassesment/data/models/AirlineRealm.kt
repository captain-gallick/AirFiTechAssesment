package com.nirvana.airfitechassesment.data.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class AirlineRealm : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var country: String = ""
    var headquarters: String = ""
    var fleet_size: Int = 0
    var website: String = ""
    var logo_url: String = ""

    fun toDomain() = Airline(id, name, country, headquarters, fleet_size, website, logo_url)

    companion object {
        fun from(domain: Airline) = AirlineRealm().apply {
            id = domain.id
            name = domain.name
            country = domain.country
            headquarters = domain.headquarters
            fleet_size = domain.fleet_size
            website = domain.website
            logo_url = domain.logo_url
        }
    }
}
