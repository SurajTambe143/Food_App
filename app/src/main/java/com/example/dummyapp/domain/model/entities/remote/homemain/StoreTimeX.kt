package com.example.dummyapp.domain.model.entities.remote.homemain

data class StoreTimeX(
    val day: Int? = 0,
    val day_time: List<DayTime>? = listOf(),
    val is_store_open: Boolean? = false,
    val is_store_open_full_time: Boolean? = false
)