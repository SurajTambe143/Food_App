package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.databinding.ItemHomeFoodCategoryBinding
import com.example.dummyapp.domain.model.FoodCategory

class FoodCategoryAdapter(private val menuList: List<FoodCategory>): RecyclerView.Adapter<FoodCategoryAdapter.FoodViewHolder>() {
    class FoodViewHolder(val binding: ItemHomeFoodCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding =
            ItemHomeFoodCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        with(holder) {
            with(menuList[position]) {
                binding.imgCategory.load(this.imgFood)
                binding.txtFood.text = this.txtFood
            }
        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
}