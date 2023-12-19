package com.example.dummyapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyapp.R
import com.example.dummyapp.databinding.FragmentHomeBinding
import com.example.dummyapp.domain.model.FoodCategory
import com.example.dummyapp.domain.model.HomeOrderFoodDetails
import com.example.dummyapp.domain.model.MenuItem
import com.example.dummyapp.domain.model.OffersItem
import com.example.dummyapp.domain.model.OrderFoodDetails
import com.example.dummyapp.presentation.home.adapter.HomeHorizontalOrderAdapter
import com.example.dummyapp.presentation.home.adapter.HorizontalOrderAdapter
import com.example.dummyapp.presentation.home.model.HomeView


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuList: List<MenuItem>
    private lateinit var foodCategoryList: List<FoodCategory>
    private lateinit var homeViewList: List<HomeView>
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var images: List<OffersItem>
    private lateinit var hrzOrderList: List<OrderFoodDetails>
    private lateinit var homeHrzOrderList: List<HomeOrderFoodDetails>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }


    private fun setUpUI() {
        loadLists()
        homeViewList = listOf(
            HomeView.OrderStatusView("Order Number - 5th Avenue - AI Furjan Area"),
            HomeView.BannerView(R.drawable.banner_img),
            HomeView.MenuView(menuList),
            HomeView.OffersView(images),
            HomeView.FoodCategoryView(foodCategoryList),
            HomeView.HomeHorizontalOrderView(homeHrzOrderList),
            HomeView.HomeVerticalOrderView(hrzOrderList)
        )
        binding.rvHome.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        homeAdapter = HomeAdapter(homeViewList)
        binding.rvHome.adapter = homeAdapter
    }

    private fun loadLists() {
        loadMenu()
        loadCategory()
        loadOffers()
        loadHorizontalOrder()
    }

    private fun loadHorizontalOrder() {
        hrzOrderList = listOf(
            OrderFoodDetails(
                R.drawable.food_items_rectangle_img,
                "Ultimate Chicken - Wraps, Pl",
                "500+",
                "2.5 KM Away",
                "25 - 45 Mins",
                "IQD 1000",
                "3.4 Excellent"
            ),
            OrderFoodDetails(
                R.drawable.food_items_rectangle_img,
                "Ultimate Chicken - Wraps, Pl",
                "500+",
                "2.5 KM Away",
                "25 - 45 Mins",
                "IQD 1000",
                "3.4 Excellent"
            ),
            OrderFoodDetails(
                R.drawable.food_items_rectangle_img,
                "Ultimate Chicken - Wraps, Pl",
                "500+",
                "2.5 KM Away",
                "25 - 45 Mins",
                "IQD 1000",
                "3.4 Excellent"
            ),
            OrderFoodDetails(
                R.drawable.food_items_rectangle_img,
                "Ultimate Chicken - Wraps, Pl",
                "500+",
                "2.5 KM Away",
                "25 - 45 Mins",
                "IQD 1000",
                "3.4 Excellent"
            ),
        )
        homeHrzOrderList= listOf(
            HomeOrderFoodDetails(
                "Order Again",
                hrzOrderList
            ),
            HomeOrderFoodDetails(
                "Order Again",
                hrzOrderList
            ),
            HomeOrderFoodDetails(
                "Order Again",
                hrzOrderList
            )
        )
    }

    private fun loadOffers() {
        images = listOf(
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers)
        )
    }

    private fun loadMenu() {
        menuList = listOf(
            MenuItem(R.drawable.ic_meal, "Food"),
            MenuItem(R.drawable.ic_hamper, "Alsaree3 Market"),
            MenuItem(R.drawable.ic_supplier, "Parcel"),
            MenuItem(R.drawable.ic_ingredients, "More"),
        )
    }

    private fun loadCategory() {
        foodCategoryList = listOf(
            FoodCategory(R.drawable.food_breakfast, "Breakfast"),
            FoodCategory(R.drawable.food_sweets, "Sweets"),
            FoodCategory(R.drawable.food_fishes, "Fishes"),
            FoodCategory(R.drawable.food_eastern, "Eastern"),
            FoodCategory(R.drawable.food_breakfast, "Breakfast"),
            FoodCategory(R.drawable.food_sweets, "Sweets"),
            FoodCategory(R.drawable.food_fishes, "Fishes"),
            FoodCategory(R.drawable.food_eastern, "Eastern"),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}