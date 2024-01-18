package com.example.dummyapp.data.remote.api

import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.data.remote.dto.HomeScrollResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface HomeMainService {

    //    @POST("user/get_home_screen_main_detail")
    //    @POST("user/get_home_screen_main_detail_with_banner_images")
    @POST("api/Z381SQ4J6H/user/get_home_screen_main_detail_with_banner_images_offers")
    @Headers(
        "longitude: 44.416270",
        "latitude: 33.341658",
        "app_version: 1.3.00",
        "auth_key: fQ9ZV1JRipN2w6shdI62A+7LWjBtVcY6HVGlSw+KBmBR2RVN4TQND38o6BZ+8Ba3",
        "device_type: android",
        "language: en",
    )
    suspend fun getHomeMainDetails(@Body data: com.example.dummyapp.utils.Body): HomeMainResponse

    @POST("api/Z381SQ4J6H/user/get_home_screen_store_list")
    @Headers(
        "longitude: 44.416270",
        "latitude: 33.341658",
        "app_version: 1.3.00",
        "auth_key: fQ9ZV1JRipN2w6shdI62A+7LWjBtVcY6HVGlSw+KBmBR2RVN4TQND38o6BZ+8Ba3",
        "device_type: android",
        "language: en",
    )
    suspend fun getHomeScrollDetails(@Body data: com.example.dummyapp.utils.Body): HomeScrollResponse

}