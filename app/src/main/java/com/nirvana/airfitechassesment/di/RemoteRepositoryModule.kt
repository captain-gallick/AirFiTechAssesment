package com.nirvana.airfitechassesment.di

import com.nirvana.airfitechassesment.data.repositories.remote.AirlineRemoteRepository
import com.nirvana.airfitechassesment.data.repositories.remote.AirlineRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAirlineRemoteRepository(
        impl: AirlineRemoteRepositoryImpl
    ): AirlineRemoteRepository
}
