package com.example.dummyapp.presentation.home.model

import com.example.dummyapp.domain.model.FoodCategory
import com.example.dummyapp.domain.model.HomeOrderFoodDetails
import com.example.dummyapp.domain.model.MenuItem
import com.example.dummyapp.domain.model.OffersItem
import com.example.dummyapp.domain.model.OrderFoodDetails

sealed class HomeView {
    data class OrderStatusView(val orderNumber:String) : HomeView()
    data class BannerView(val imgBanner:Int) : HomeView()
    data class MenuView(val menuList: List<MenuItem>) : HomeView()
    data class OffersView(val offersList: List<OffersItem>): HomeView()
    data class FoodCategoryView(val foodCategoryList: List<FoodCategory>):HomeView()
    data class HomeHorizontalOrderView(val homeOrderFoodDetails:List<HomeOrderFoodDetails>):HomeView()
    data class HomeVerticalOrderView(val orderFoodDetails:List<OrderFoodDetails>):HomeView()
}