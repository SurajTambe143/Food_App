package com.example.dummyapp.data.repository

import com.example.dummyapp.data.remote.api.HomeMainService
import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.domain.repository.HomeMainRepository
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
}