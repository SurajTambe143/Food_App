package com.example.dummyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.dummyapp.data.remote.api.HomeMainService
import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.data.remote.dto.HomeScrollResponse
import com.example.dummyapp.domain.repository.HomeMainRepository
import com.example.dummyapp.paging.HomeScrollDetailsPagingSource
import com.example.dummyapp.utils.Body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeMainRepositoryImpl(private val homeMainService: HomeMainService) : HomeMainRepository {
    override fun getHomeMainDetails() = flow {
        emit(
            homeMainService.getHomeMainDetails(
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
}