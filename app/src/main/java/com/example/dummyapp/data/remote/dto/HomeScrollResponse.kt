package com.example.dummyapp.data.remote.dto

import com.example.dummyapp.domain.model.entities.remote.homescroll.Store

data class HomeScrollResponse(
    val message: Int? = 0,
    val server_time: String? = "",
    val store_count: Int? = 0,
    val stores: List<Store> = listOf(),
    val success: Boolean? = false
)