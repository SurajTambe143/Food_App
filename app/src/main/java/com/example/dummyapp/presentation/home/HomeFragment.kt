package com.example.dummyapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Orientation
import androidx.viewpager2.widget.ViewPager2
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ActivityMainBinding
import com.example.dummyapp.databinding.FragmentHomeBinding
import com.example.dummyapp.domain.model.FoodCategory
import com.example.dummyapp.domain.model.MenuItem
import com.example.dummyapp.domain.model.OffersItem
import com.example.dummyapp.presentation.home.adapter.FoodCategoryAdapter
import com.example.dummyapp.presentation.home.adapter.HomeOffersAdapter
import com.example.dummyapp.presentation.home.adapter.MenuAdapter
import com.example.dummyapp.presentation.home.adapter.MenuItemOffsetDecoration
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.intellij.lang.annotations.Language

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuList: List<MenuItem>
    private lateinit var foodCategoryList: List<FoodCategory>
    private lateinit var foodCategoryAdapter: FoodCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViews()
        setUpOffersViewPager()

    }

    private fun setUpOffersViewPager() {
        val viewPager2 = binding.offersViewpager
        val tabLayout = binding.tabLayout
        val images = listOf(
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers),
            OffersItem(R.drawable.bg_offers)
        )
        val adapter = HomeOffersAdapter()
        adapter.setItem(images)
        viewPager2.adapter = adapter

        // Attach TabLayout to ViewPager2
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            // Customize tab if needed (e.g., set text)
            // tab.text = "Tab $position"
        }.attach()
    }

    private fun setUpRecyclerViews() {
        menu()
        foodCategory()
    }

    private fun foodCategory() {
        loadCategory()
        binding.rvFoodCategory.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        foodCategoryAdapter = FoodCategoryAdapter(foodCategoryList)
        binding.rvFoodCategory.adapter = foodCategoryAdapter
    }

    private fun menu() {
        loadMenu()
        binding.rvMenu.layoutManager = GridLayoutManager(requireActivity(), 4)
        menuAdapter = MenuAdapter(menuList)
        binding.rvMenu.adapter = menuAdapter
        binding.rvMenu.addItemDecoration(
            MenuItemOffsetDecoration(
                requireActivity(),
                R.dimen.item_offset
            )
        )
        menuAdapter.notifyDataSetChanged()
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