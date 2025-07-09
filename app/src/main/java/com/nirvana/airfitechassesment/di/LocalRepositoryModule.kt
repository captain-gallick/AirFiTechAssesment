package com.nirvana.airfitechassesment.di

import com.nirvana.airfitechassesment.data.repositories.local.AirlineLocalRepository
import com.nirvana.airfitechassesment.data.repositories.local.AirlineLocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAirlineLocalRepository(
        impl: AirlineLocalRepositoryImpl
    ): AirlineLocalRepository
}
