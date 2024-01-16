package com.example.dummyapp.presentation.home.model

import com.example.dummyapp.domain.model.HomeOrderFoodDetails
import com.example.dummyapp.domain.model.MenuItem
import com.example.dummyapp.domain.model.OrderFoodDetails
import com.example.dummyapp.domain.model.entities.remote.homemain.Banner
import com.example.dummyapp.domain.model.entities.remote.homemain.Brand

sealed class HomeView {
    data class OrderStatusView(val orderNumber:String?) : HomeView()
    data class BannerView(val imgBanner:String?) : HomeView()
    data class MenuView(val menuList: List<MenuItem>) : HomeView()
    data class OffersView(val offersList: List<Banner>?): HomeView()
    data class FoodCategoryView(val foodCategoryList: List<Brand>?):HomeView()
    data class HomeHorizontalOrderView(val homeOrderFoodDetails:List<HomeOrderFoodDetails>):HomeView()
    data class HomeVerticalOrderView(val orderFoodDetails:List<OrderFoodDetails>):HomeView()
}