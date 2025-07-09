package com.nirvana.airfitechassesment.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nirvana.airfitechassesment.AirFiAssessmentApp
import com.nirvana.airfitechassesment.data.models.AirlineRealm
import com.nirvana.airfitechassesment.data.network.AirlineApi
import com.nirvana.airfitechassesment.data.repositories.AirlineRepository
import com.nirvana.airfitechassesment.data.repositories.AirlineRepositoryImpl
import com.nirvana.airfitechassesment.data.repositories.local.AirlineLocalRepository
import com.nirvana.airfitechassesment.data.repositories.remote.AirlineRemoteRepository
import com.nirvana.airfitechassesment.utils.JsonUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AirFIModules {

    @Provides
    fun provideBaseUrl() = "https://686e3175c9090c495388a676.mockapi.io/api/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideAirlineApi(retrofit: Retrofit): AirlineApi =
        retrofit.create(AirlineApi::class.java)

    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder(schema = setOf(AirlineRealm::class))
            .name("airlines.realm")
            .schemaVersion(1)
            .build()
    }

    @Provides
    @Singleton
    fun provideRealm(config: RealmConfiguration): Realm {
        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun provideAirlineRepository(
        remote: AirlineRemoteRepository,
        local: AirlineLocalRepository,
        jsonUtils: JsonUtils
    ): AirlineRepository {
        return AirlineRepositoryImpl(remote, local, jsonUtils)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}