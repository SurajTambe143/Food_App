package com.example.dummyapp.presentation.home.concatadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.databinding.ItemViewHomeMenuBinding
import com.example.dummyapp.databinding.ItemViewOffersBinding
import com.example.dummyapp.presentation.home.adapter.HomeOffersAdapter
import com.example.dummyapp.presentation.home.adapter.MenuAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OffersConcatAdapter(private val offersAdapter: HomeOffersAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val binding =
            ItemViewOffersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ConcatViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(offersAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(val binding: ItemViewOffersBinding) :
        BaseConcatHolder<HomeOffersAdapter>(binding.root) {
        override fun bind(adapter: HomeOffersAdapter) {
            binding.offersViewpager.adapter= adapter
            TabLayoutMediator(binding.tabLayout, binding.offersViewpager) { tab, position ->
                // Customize tab if needed (e.g., set text)
                // tab.text = "Tab $position"
            }.attach()
        }
    }
}