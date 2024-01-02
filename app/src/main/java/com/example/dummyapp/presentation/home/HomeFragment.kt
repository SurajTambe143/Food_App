package com.example.dummyapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.R
import com.example.dummyapp.databinding.FragmentHomeBinding
import com.example.dummyapp.databinding.ItemHomeStickyRvBinding
import com.example.dummyapp.domain.model.FoodCategory
import com.example.dummyapp.domain.model.HomeOrderFoodDetails
import com.example.dummyapp.domain.model.MenuItem
import com.example.dummyapp.domain.model.OffersItem
import com.example.dummyapp.domain.model.OrderFoodDetails
import com.example.dummyapp.domain.model.StickyItem
import com.example.dummyapp.presentation.home.adapter.HomeHorizontalOrderAdapter
import com.example.dummyapp.presentation.home.adapter.HorizontalOrderAdapter
import com.example.dummyapp.presentation.home.adapter.StickyViewAdapter
import com.example.dummyapp.presentation.home.model.HomeView
import kotlin.math.log


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuList: List<MenuItem>
    private lateinit var stickyList: List<StickyItem>
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
        binding.stickyHeader.let {
            it.layoutManager = GridLayoutManager(requireActivity(), 4)
            it.adapter = StickyViewAdapter(stickyList)
        }
        val layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvHome.layoutManager = layoutManager
        homeAdapter = HomeAdapter(homeViewList) {
            findNavController().navigate(R.id.action_homeFragment_to_foodMenuFragment)
        }
        binding.rvHome.adapter = homeAdapter
        binding.rvHome.addOnScrollListener(
            RvStickyScroll(
                homeViewList,
                binding.stickyHeader,
                layoutManager
            )
        )
    }

    internal class RvStickyScroll(
        val list: List<HomeView>,
        private val stickyView: RecyclerView,
        val rvLayoutManager: LinearLayoutManager
    ) : RecyclerView.OnScrollListener() {
        private val TAG = "HomeFragment"
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            val position = rvLayoutManager.findFirstCompletelyVisibleItemPosition()
            Log.e(TAG, "onScrollStateChanged: " + position)

            // Check if position is valid and list is not empty
            if (position != RecyclerView.NO_POSITION && position < list.size) {
                val itemView = list[position]
                // Use 'when' expression to handle different item types
                when (itemView) {
                    is HomeView.OrderStatusView -> stickyView.visibility = View.GONE
                    is HomeView.BannerView -> stickyView.visibility = View.GONE
                    is HomeView.MenuView -> stickyView.visibility = View.GONE
                    is HomeView.OffersView,
                    is HomeView.FoodCategoryView,
                    is HomeView.HomeHorizontalOrderView,
                    is HomeView.HomeVerticalOrderView -> stickyView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun loadLists() {
        loadMenu()
        loadSticky()
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
                R.drawable.food_items_rectangle_img_1,
                "9021PHO Restaurant",
                "500+",
                "2.5 KM Away",
                "25 - 45 Mins",
                "IQD 1000",
                "3.4 Excellent"
            ),
            OrderFoodDetails(
                R.drawable.food_items_rectangle_img_2,
                "Burgatory Restaurant",
                "500+",
                "2.5 KM Away",
                "25 - 45 Mins",
                "IQD 1000",
                "3.4 Excellent"
            ),
            OrderFoodDetails(
                R.drawable.food_items_rectangle_img_3,
                "Latitude Cafe",
                "500+",
                "2.5 KM Away",
                "25 - 45 Mins",
                "IQD 1000",
                "3.4 Excellent"
            ),
        )
        homeHrzOrderList = listOf(
            HomeOrderFoodDetails(
                "Order Again",
                hrzOrderList
            ),
            HomeOrderFoodDetails(
                "Featured",
                hrzOrderList
            ),
            HomeOrderFoodDetails(
                "Offers Nearby",
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

    private fun loadSticky() {
        stickyList = listOf(
            StickyItem("Food"),
            StickyItem("Alsaree3 Market"),
            StickyItem("Parcel"),
            StickyItem("More"),
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