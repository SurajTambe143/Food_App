package com.example.dummyapp.data.remote.dto

import com.example.dummyapp.domain.model.entities.remote.homemain.Ad
import com.example.dummyapp.domain.model.entities.remote.homemain.Banner
import com.example.dummyapp.domain.model.entities.remote.homemain.Brand
import com.example.dummyapp.domain.model.entities.remote.homemain.CampaignDetail
import com.example.dummyapp.domain.model.entities.remote.homemain.HorizontalStore
import com.example.dummyapp.domain.model.entities.remote.homemain.HorizontalStores2
import com.example.dummyapp.domain.model.entities.remote.homemain.StoreOffer
import com.example.dummyapp.domain.model.entities.remote.homemain.Tag

data class HomeMainResponse(
    val ads: List<Ad>? = listOf(),
    val ads_title: String? = "",
    val banner: List<Banner>? = listOf(),
    val brands: List<Brand>? = listOf(),
    val campaign_detail: CampaignDetail? = CampaignDetail(),
    val home_screen_store_type: Int? = 0,
    val horizontal_store_title: String? = "",
    val horizontal_store_title_2: String? = "",
    val horizontal_stores: List<HorizontalStore>? = listOf(),
    val horizontal_stores_2: List<HorizontalStores2>? = listOf(),
    val is_show_ad_text: Boolean? = false,
    val is_show_curfew_image: Boolean? = false,
    val message: Int? = 0,
    val popular_searches: List<String>? = listOf(),
    val server_time: String? = "",
    val show_brand_scroll_after: Int? = 0,
    val store_listing_title: String? = "",
    val store_offers: List<StoreOffer>? = listOf(),
    val success: Boolean? = false,
    val tags: List<Tag>? = listOf(),
    val tags_title: String? = "",
    val zone_id: String? = ""
)