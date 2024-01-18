package com.example.dummyapp.data.remote.api

import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.data.remote.dto.HomeScrollResponse
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.WeakHashMap

interface HomeMainService {

    //    @POST("user/get_home_screen_main_detail")
    //    @POST("user/get_home_screen_main_detail_with_banner_images")
    @POST("api/Z381SQ4J6H/user/get_home_screen_main_detail_with_banner_images_offers")
    suspend fun getHomeMainDetails(
        @HeaderMap headers: HashMap<String, String>?,
        @Body data: com.example.dummyapp.utils.Body
    ): HomeMainResponse

    @POST("api/Z381SQ4J6H/user/get_home_screen_store_list")
    suspend fun getHomeScrollDetails(
        @HeaderMap headers: HashMap<String, String>?,
        @Body data: com.example.dummyapp.utils.Body
    ): HomeScrollResponse

}