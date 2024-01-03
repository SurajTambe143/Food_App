package com.example.dummyapp.presentation.home.concatadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.databinding.ItemViewFoodCategoryBinding
import com.example.dummyapp.presentation.home.adapter.FoodCategoryAdapter

class LinearConcatAdapter(private val foodCategoryAdapter: FoodCategoryAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val binding =
            ItemViewFoodCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.rvFoodCategory.layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.HORIZONTAL, false
        )
        return ConcatViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(foodCategoryAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(val binding: ItemViewFoodCategoryBinding) :
        BaseConcatHolder<FoodCategoryAdapter>(binding.root) {
        override fun bind(adapter: FoodCategoryAdapter) {
            binding.rvFoodCategory.adapter = adapter
        }
    }
}