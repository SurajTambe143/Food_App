package com.example.dummyapp.domain.repository

import androidx.paging.PagingData
import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.data.remote.dto.HomeScrollResponse
import com.example.dummyapp.domain.model.entities.remote.homescroll.Store
import kotlinx.coroutines.flow.Flow

interface HomeMainRepository {

    fun getHomeMainDetails(): Flow<HomeMainResponse>

    fun getHomeScrollDetails():Flow<PagingData<Store>>
}