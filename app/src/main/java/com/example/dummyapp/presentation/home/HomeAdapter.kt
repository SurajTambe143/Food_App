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
import com.example.dummyapp.R
import com.example.dummyapp.presentation.home.adapter.FoodCategoryAdapter
import com.example.dummyapp.presentation.home.adapter.HomeOffersAdapter
import com.example.dummyapp.presentation.home.adapter.MenuAdapter
import com.example.dummyapp.presentation.home.adapter.MenuItemOffsetDecoration
import com.example.dummyapp.presentation.home.model.HomeView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeAdapter(private val items: List<HomeView>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //View Holder classes for each type of view
    inner class OrderStatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: HomeView.OrderStatusView) {
            //Bind data to views
        }
    }

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: HomeView.BannerView) {
//            val imageView = itemView.findViewById<ImageView>(R.id.img_banner)
//            imageView.load(item.bannerImg)

        }
    }

    inner class MenuViewHolder(itemView: View, val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView =
            itemView.findViewById(R.id.rv_menu) // Replace with your actual RecyclerView ID

        fun bind(item: HomeView.MenuView) {
            // Bind data to the inner RecyclerView
            recyclerView.layoutManager = GridLayoutManager(context, 4)
            val innerAdapter = MenuAdapter(item.menuList) // Replace with your inner items list
            recyclerView.adapter = innerAdapter
            recyclerView.addItemDecoration(
                MenuItemOffsetDecoration(
                    context,
                    R.dimen.item_offset
                )
            )
        }
    }

    inner class OffersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewPager2: ViewPager2 = itemView.findViewById(R.id.offers_viewpager)
        val tabLayout: TabLayout = itemView.findViewById(R.id.tab_layout)
        fun bind(item: HomeView.OffersView) {
            //Bind data to views
            val adapter = HomeOffersAdapter()
            adapter.setItem(item.offersList)
            viewPager2.adapter = adapter

            // Attach TabLayout to ViewPager2
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                // Customize tab if needed (e.g., set text)
                // tab.text = "Tab $position"
            }.attach()
        }
    }

    inner class FoodCategoryViewHolder(itemView: View, val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView =
            itemView.findViewById(R.id.rv_food_category) // Replace with your actual RecyclerView ID

        fun bind(item: HomeView.FoodCategoryView) {
            // Bind data to the inner RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL, false
            )
            val innerAdapter =
                FoodCategoryAdapter(item.foodCategoryList) // Replace with your inner items list
            recyclerView.adapter = innerAdapter
//            recyclerView.addItemDecoration(
//                MenuItemOffsetDecoration(
//                    context,
//                    R.dimen.item_offset
//                )
//
            val snapHelper = LinearSnapHelper() // Or PagerSnapHelper
            snapHelper.attachToRecyclerView(recyclerView)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> OrderStatusViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_order_status, parent, false)
            )

            1 -> BannerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
            )

            2 -> MenuViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_home_menu, parent, false),
                parent.context
            )

            3 -> OffersViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_offers, parent, false)
            )

            4 -> FoodCategoryViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_food_category, parent, false),
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
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeView.OrderStatusView -> 0
            is HomeView.BannerView -> 1
            is HomeView.MenuView -> 2
            is HomeView.OffersView -> 3
            is HomeView.FoodCategoryView -> 4
        }
    }
}