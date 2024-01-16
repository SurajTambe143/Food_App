package com.example.dummyapp.domain.repository

import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.data.remote.dto.HomeScrollResponse
import kotlinx.coroutines.flow.Flow

interface HomeMainRepository {

    fun getHomeMainDetails(): Flow<HomeMainResponse>

    fun getHomeScrollDetails():Flow<HomeScrollResponse>
}