package com.example.dummyapp.di

import com.example.dummyapp.data.remote.api.HomeMainService
import com.example.dummyapp.data.repository.HomeMainRepositoryImpl
import com.example.dummyapp.domain.repository.HomeMainRepository
import com.example.dummyapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient=OkHttpClient.Builder().addInterceptor(logging).build()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
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

    /**
     * SplashOKHTTPClient:OKHttClient
     * HomeOKHTTPClient
     */


//    fun test(){
//        val splash=OkHttpClient.Builder().build()
//        val home=OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
//    }
//

//    class Location{
//        val lat:Stirng,
//                val long:String
//    }
//    @Qualifier
//    annotation class Login
//
//    {
//        "lat":""
//        "long":""
//    }

//    data class LocationData(val lat:String,val long:String)
//
//    //Mapper
//    data class LocationEntirty(val location:String)
}