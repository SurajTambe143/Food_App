package com.example.dummyapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ItemBannerBinding
import com.example.dummyapp.databinding.ItemOffersBinding
import com.example.dummyapp.databinding.ItemOrderStatusBinding
import com.example.dummyapp.databinding.ItemViewFoodCategoryBinding
import com.example.dummyapp.databinding.ItemViewHomeHorizontalOrderBinding
import com.example.dummyapp.databinding.ItemViewHomeMenuBinding
import com.example.dummyapp.databinding.ItemViewHomeVerticalOrderBinding
import com.example.dummyapp.databinding.ItemViewOffersBinding
import com.example.dummyapp.presentation.home.adapter.FoodCategoryAdapter
import com.example.dummyapp.presentation.home.adapter.HomeHorizontalOrderAdapter
import com.example.dummyapp.presentation.home.adapter.HomeOffersAdapter
import com.example.dummyapp.presentation.home.adapter.HorizontalOrderAdapter
import com.example.dummyapp.presentation.home.adapter.MenuAdapter
import com.example.dummyapp.presentation.home.adapter.MenuItemOffsetDecoration
import com.example.dummyapp.presentation.home.adapter.VerticalOrderAdapter
import com.example.dummyapp.presentation.home.model.HomeView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeAdapter(private val items: List<HomeView>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private lateinit var homeOffersAdapter: HomeOffersAdapter
        private lateinit var snapHelper: LinearSnapHelper

    //View Holder classes for each type of view
    inner class OrderStatusViewHolder(val binding: ItemOrderStatusBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeView.OrderStatusView) {
            //Bind data to views
            binding.txtOrderNumber.text=item.orderNumber
        }
    }

    inner class BannerViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeView.BannerView) {
            binding.imgBanner.load(item.imgBanner)
        }
    }

    inner class MenuViewHolder(val binding: ItemViewHomeMenuBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rvMenu.layoutManager = GridLayoutManager(context, 4)
        }

        fun bind(item: HomeView.MenuView) {
            // Bind data to the inner RecyclerView
            binding.rvMenu.adapter = MenuAdapter(item.menuList)
//            recyclerView.addItemDecoration(
//                MenuItemOffsetDecoration(
//                    context,
//                    R.dimen.item_offset
//                )
//            )
        }
    }

    inner class OffersViewHolder(val binding: ItemViewOffersBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            homeOffersAdapter = HomeOffersAdapter()
            binding.offersViewpager.adapter = homeOffersAdapter
        }
        fun bind(item: HomeView.OffersView) {

            homeOffersAdapter.setItem(item.offersList)

            // Attach TabLayout to ViewPager2
            TabLayoutMediator(binding.tabLayout, binding.offersViewpager) { tab, position ->
                // Customize tab if needed (e.g., set text)
                // tab.text = "Tab $position"
            }.attach()

        }
    }

    inner class FoodCategoryViewHolder(val binding:ItemViewFoodCategoryBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.rvFoodCategory.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL, false
            )
            snapHelper = LinearSnapHelper()
        }

        fun bind(item: HomeView.FoodCategoryView) {
            // Or PagerSnapHelper
            snapHelper.attachToRecyclerView(binding.rvFoodCategory)

            val innerAdapter =
                FoodCategoryAdapter(item.foodCategoryList)
            binding.rvFoodCategory.adapter = innerAdapter

        }
    }

    inner class HomeHorizontalOrderViewHolder(val binding:ItemViewHomeHorizontalOrderBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewRvHomeHrzOrder.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        fun bind(item: HomeView.HomeHorizontalOrderView) {
            // Bind data to the inner RecyclerView

            binding.viewRvHomeHrzOrder.adapter =
                HomeHorizontalOrderAdapter(item.homeOrderFoodDetails)
        }
    }

    inner class HomeVerticalOrderViewHolder(val binding:ItemViewHomeVerticalOrderBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewRvHomeVertOrder.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        fun bind(item: HomeView.HomeVerticalOrderView) {
            // Bind data to the inner RecyclerView

            binding.viewRvHomeVertOrder.adapter =
                VerticalOrderAdapter(item.orderFoodDetails)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> OrderStatusViewHolder(
                ItemOrderStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            1 -> BannerViewHolder(
                ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            2 -> MenuViewHolder(
                ItemViewHomeMenuBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false),
                parent.context
            )

            3 -> OffersViewHolder(
                ItemViewOffersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            4 -> FoodCategoryViewHolder(
                ItemViewFoodCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                parent.context
            )

            5 -> HomeHorizontalOrderViewHolder(
                ItemViewHomeHorizontalOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                parent.context
            )

            6 -> HomeVerticalOrderViewHolder(
                ItemViewHomeVerticalOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                parent.context
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is HomeView.OrderStatusView -> (holder as OrderStatusViewHolder).bind(item)
            is HomeView.BannerView -> (holder as BannerViewHolder).bind(item)
            is HomeView.MenuView -> (holder as MenuViewHolder).bind(item)
            is HomeView.OffersView -> (holder as OffersViewHolder).bind(item)
            is HomeView.FoodCategoryView -> (holder as FoodCategoryViewHolder).bind(item)
            is HomeView.HomeHorizontalOrderView -> (holder as HomeHorizontalOrderViewHolder).bind(
                item
            )
            is HomeView.HomeVerticalOrderView -> (holder as HomeVerticalOrderViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeView.OrderStatusView -> VIEW_TYPE_ORDER_STATUS
            is HomeView.BannerView -> VIEW_TYPE_BANNER
            is HomeView.MenuView -> VIEW_TYPE_MENU
            is HomeView.OffersView -> VIEW_TYPE_OFFERS
            is HomeView.FoodCategoryView -> VIEW_TYPE_FOOD_CATEGORY
            is HomeView.HomeHorizontalOrderView -> VIEW_TYPE_HOME_HORIZONTAL_ORDER
            is HomeView.HomeVerticalOrderView -> VIEW_TYPE_HOME_VERTICAL_ORDER
        }
    }

    companion object {
        private const val VIEW_TYPE_ORDER_STATUS = 0
        private const val VIEW_TYPE_BANNER = 1
        private const val VIEW_TYPE_MENU = 2
        private const val VIEW_TYPE_OFFERS = 3
        private const val VIEW_TYPE_FOOD_CATEGORY = 4
        private const val VIEW_TYPE_HOME_HORIZONTAL_ORDER = 5
        private const val VIEW_TYPE_HOME_VERTICAL_ORDER = 6
    }
}