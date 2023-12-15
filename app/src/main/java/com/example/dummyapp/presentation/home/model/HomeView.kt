package com.example.dummyapp.presentation.home.model

import com.example.dummyapp.domain.model.FoodCategory
import com.example.dummyapp.domain.model.MenuItem
import com.example.dummyapp.domain.model.OffersItem

sealed class HomeView {
    object OrderStatusView : HomeView()
    object BannerView : HomeView()
    data class MenuView(val menuList: List<MenuItem>) : HomeView()
    data class OffersView(val offersList: List<OffersItem>): HomeView()
    data class FoodCategoryView(val foodCategoryList: List<FoodCategory>):HomeView()
}