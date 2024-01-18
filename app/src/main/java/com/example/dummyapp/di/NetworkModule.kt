package com.example.dummyapp.di

import com.example.dummyapp.data.remote.api.HomeMainService
import com.example.dummyapp.data.repository.HomeMainRepositoryImpl
import com.example.dummyapp.domain.repository.HomeMainRepository
import com.example.dummyapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getHomeMainDetails(retrofit: Retrofit): HomeMainService {
        return retrofit.create(HomeMainService::class.java)
    }

    @Singleton
    @Provides
    fun getHomeMainRepository(homeMainService: HomeMainService): HomeMainRepository =
        HomeMainRepositoryImpl(homeMainService)
}