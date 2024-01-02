package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ItemHomeFoodCategoryBinding
import com.example.dummyapp.domain.model.FoodCategory
import com.example.dummyapp.domain.model.MenuItem

class FoodCategoryAdapter() :
    RecyclerView.Adapter<FoodCategoryAdapter.FoodViewHolder>() {

    private var foodCategoryList: List<FoodCategory> = emptyList<FoodCategory>().toMutableList()

    class FoodViewHolder(val binding: ItemHomeFoodCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding =
            ItemHomeFoodCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        with(holder) {
            if (position == 0) {
                val marginValue =
                    holder.itemView.resources.getDimensionPixelSize(R.dimen.margin_left_value)
                val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.leftMargin = marginValue
                binding.root.layoutParams = layoutParams
            }
            with(foodCategoryList[position]) {
                binding.imgCategory.load(this.imgFood)
                binding.txtFood.text = this.txtFood
            }
        }
    }

    override fun getItemCount(): Int {
        return foodCategoryList.size
    }

    fun updateList(list: List<FoodCategory>) {
        foodCategoryList = list
        notifyDataSetChanged()
    }
}