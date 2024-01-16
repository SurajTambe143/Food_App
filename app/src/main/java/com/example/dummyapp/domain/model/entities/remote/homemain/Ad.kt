package com.example.dummyapp.domain.model.entities.remote.homemain

data class Ad(
    val _id: String? = "",
    val ads_detail: String? = "",
    val end_time: String? = "",
    val expiry_date: String? = "",
    val image_url: String? = "",
    val is_ads_cc: Boolean? = false,
    val is_ads_on: Boolean? = false,
    val is_ads_redirect_to_store: Boolean? = false,
    val is_ads_visible: Boolean? = false,
    val is_store_open: Boolean? = false,
    val location: List<Double?>? = listOf(),
    val new_design_image_url: String? = "",
    val start_time: String? = "",
    val store_id: String? = "",
    val store_offers_detail: StoreOffersDetail? = StoreOffersDetail(),
    val store_radius: Int? = 0
)