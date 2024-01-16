package com.example.dummyapp.presentation.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.R
import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.data.repository.HomeMainRepositoryImpl
import com.example.dummyapp.databinding.FragmentHomeBinding
import com.example.dummyapp.domain.model.FoodCategory
import com.example.dummyapp.domain.model.HomeOrderFoodDetails
import com.example.dummyapp.domain.model.MenuItem
import com.example.dummyapp.domain.model.OffersItem
import com.example.dummyapp.domain.model.OrderFoodDetails
import com.example.dummyapp.domain.model.StickyItem
import com.example.dummyapp.utils.gone
import com.example.dummyapp.utils.visible
import com.example.dummyapp.presentation.home.adapter.StickyViewAdapter
import com.example.dummyapp.presentation.home.model.HomeView
import com.example.dummyapp.presentation.home.viewmodel.HomeMainViewModel
import com.example.dummyapp.presentation.home.viewmodel.HomeMainViewModelFactory
import com.example.dummyapp.utils.RetrofitBuilder
import com.example.dummyapp.utils.encryptData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var menuList: List<MenuItem> = emptyList()
    private var stickyList: List<StickyItem> = emptyList()
    private var foodCategoryList: List<FoodCategory> = emptyList()
    private var homeViewList: List<HomeView?> = emptyList()
    private var images: List<OffersItem> = emptyList()
    private var hrzOrderList: List<OrderFoodDetails> = emptyList()
    private var homeHrzOrderList: List<HomeOrderFoodDetails> = emptyList()
    private lateinit var homeAdapter: HomeAdapter
    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
//            setUpLists()
        }
//        setUpUI()
        apiDemo()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun apiDemo() {
        val encryptedData =
            encryptData("xAwrEKbSeRFbPASuggNTXNPcHeJNaUNU", "XHfZhnLrWOJyPZAW", "WMVYXBCU2ZUFVJ72")
        Log.w(TAG, "KEY :  $encryptedData")

        val apiHelper = HomeMainRepositoryImpl(RetrofitBuilder.apiService)
        val myViewModel = ViewModelProvider(
            this, HomeMainViewModelFactory(apiHelper)
        )[HomeMainViewModel::class.java]

        myViewModel.listResponse.observe(viewLifecycleOwner) {
            setUpLists(it)
            Log.e(TAG, "apiDemo: $it")
        }
    }

    private fun setUpLists(list: HomeMainResponse) {
        loadLists()
        loadHorizontalOrder(list)
        homeViewList = listOf(
            HomeView.OrderStatusView("Order Number - 5th Avenue - AI Furjan Area"),
            HomeView.BannerView(list.campaign_detail?.offer_image_url),
            if (menuList.isEmpty()) null else HomeView.MenuView(menuList),
            HomeView.OffersView(list.banner),
            HomeView.FoodCategoryView(list.brands),
            HomeView.HomeHorizontalOrderView(homeHrzOrderList),
//            if (hrzOrderList.isEmpty()) null else HomeView.HomeVerticalOrderView(hrzOrderList)
        )
        setUpUI()
    }


    private fun setUpUI() {
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
                homeViewList, binding.stickyHeader, layoutManager
            )
        )
    }

    internal class RvStickyScroll(
        val list: List<HomeView?>,
        private val stickyView: RecyclerView,
        private val rvLayoutManager: LinearLayoutManager
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
                    is HomeView.OrderStatusView -> stickyView.gone()
                    is HomeView.BannerView -> stickyView.gone()
                    is HomeView.MenuView -> stickyView.gone()
                    is HomeView.OffersView, is HomeView.FoodCategoryView, is HomeView.HomeHorizontalOrderView, is HomeView.HomeVerticalOrderView -> stickyView.visible(
                        true
                    )

                    else -> {}
                }
            }
        }
    }

    private fun loadLists() {
//        loadMenu()
//        loadSticky()
//        loadCategory()
//        loadOffers()
//        loadHorizontalOrder()
    }

    private fun loadHorizontalOrder(list: HomeMainResponse) {
//        hrzOrderList = listOf(
//            OrderFoodDetails(
//                R.drawable.food_items_rectangle_img,
//                "Ultimate Chicken - Wraps, Pl",
//                "500+",
//                "2.5 KM Away",
//                "25 - 45 Mins",
//                "IQD 1000",
//                "3.4 Excellent"
//            ),
//            OrderFoodDetails(
//                R.drawable.food_items_rectangle_img_1,
//                "9021PHO Restaurant",
//                "500+",
//                "2.5 KM Away",
//                "25 - 45 Mins",
//                "IQD 1000",
//                "3.4 Excellent"
//            ),
//            OrderFoodDetails(
//                R.drawable.food_items_rectangle_img_2,
//                "Burgatory Restaurant",
//                "500+",
//                "2.5 KM Away",
//                "25 - 45 Mins",
//                "IQD 1000",
//                "3.4 Excellent"
//            ),
//            OrderFoodDetails(
//                R.drawable.food_items_rectangle_img_3,
//                "Latitude Cafe",
//                "500+",
//                "2.5 KM Away",
//                "25 - 45 Mins",
//                "IQD 1000",
//                "3.4 Excellent"
//            ),
//        )
//        homeHrzOrderList = listOf(
//            HomeOrderFoodDetails(
//                list.ads_title, list.ads
//            ), HomeOrderFoodDetails(
//                list.store_listing_title, list.store_offers
//            ), HomeOrderFoodDetails(
//                list.horizontal_store_title, list.horizontal_stores
//            ), HomeOrderFoodDetails(
//                list.horizontal_store_title_2, list.horizontal_stores_2
//            )
//        )
    }

    private suspend fun loadOffers() {
        images = listOf(
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers)
        )
//        delay(200)
    }

    private suspend fun loadMenu() {
        menuList = listOf(
            MenuItem(R.drawable.ic_meal, "Food"),
            MenuItem(R.drawable.ic_hamper, "Alsaree3 Market"),
            MenuItem(R.drawable.ic_supplier, "Parcel"),
            MenuItem(R.drawable.ic_ingredients, "More"),
        )
//        delay(100)
    }

    private suspend fun loadSticky() {
        stickyList = listOf(
            StickyItem("Food"),
            StickyItem("Alsaree3 Market"),
            StickyItem("Parcel"),
            StickyItem("More"),
        )
//        delay(100)
    }

    private suspend fun loadCategory() {
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
//        delay(500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}