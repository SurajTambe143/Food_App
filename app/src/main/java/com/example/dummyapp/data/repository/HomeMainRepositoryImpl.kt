package com.example.dummyapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.dummyapp.data.remote.api.HomeMainService
import com.example.dummyapp.domain.repository.HomeMainRepository
import com.example.dummyapp.paging.HomeScrollDetailsPagingSource
import com.example.dummyapp.utils.Body
import com.example.dummyapp.utils.getHeaders
import kotlinx.coroutines.flow.flow

class HomeMainRepositoryImpl(private val homeMainService: HomeMainService) : HomeMainRepository {
    override fun getHomeMainDetails() = flow {
        Log.e("RepoImpl", "getHomeMainDetails KEY   : ${getHeaders()}", )
        emit(
            homeMainService.getHomeMainDetails(
                getHeaders(),
                Body(
                    33.341658,
                    44.416270,
                    "en",
                    1,
                    "5ab0d991bfe73957dc8ea53d",
                    "5abcd381d761ca635c980349"
                )
            )
        )
    }

    override fun getHomeScrollDetails()= Pager(
            config = PagingConfig(pageSize = 10, maxSize = 30),
            pagingSourceFactory = { HomeScrollDetailsPagingSource(homeMainService) }
    ).flow

//    override fun getHomeScrollDetails()= flow {
//        emit(
//            homeMainService.getHomeScrollDetails(
//                Body(
//                    33.341658,
//                    44.416270,
//                    "en",
//                    1,
//                    "5ab0d991bfe73957dc8ea53d",
//                    "5abcd381d761ca635c980349"
//                )
//            )
//        )
//    }


    /**
     * LoginHTTPClient
     *   Interceptor
     * NormalHttpClient
     *   TokenInterceptor
     */
}