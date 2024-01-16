package com.example.dummyapp.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ItemHomeFoodCategoryBinding
import com.example.dummyapp.domain.model.entities.remote.homemain.Brand
import com.example.dummyapp.utils.Constants.BASE_IMAGE_URL

class FoodCategoryAdapter() :
    RecyclerView.Adapter<FoodCategoryAdapter.FoodViewHolder>() {

    private var foodCategoryList: List<Brand>? = emptyList<Brand>().toMutableList()

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
            with(foodCategoryList?.get(position) ?: null) {
                binding.imgCategory.load(BASE_IMAGE_URL+ (this?.image_url ?: 0))
                binding.txtFood.text = this?.name ?: ""
            }
        }
    }

    override fun getItemCount(): Int {
        return foodCategoryList?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Brand>?) {
        foodCategoryList = list
        notifyDataSetChanged()
    }
}