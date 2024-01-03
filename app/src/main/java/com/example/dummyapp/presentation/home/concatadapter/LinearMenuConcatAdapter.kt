package com.example.dummyapp.presentation.home.concatadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.databinding.ItemViewHomeMenuBinding
import com.example.dummyapp.presentation.home.adapter.MenuAdapter

class LinearMenuConcatAdapter(private val menuAdapter: MenuAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val binding =
            ItemViewHomeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.rvMenu.layoutManager = GridLayoutManager(binding.root.context, 4)
        return ConcatViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(menuAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(val binding: ItemViewHomeMenuBinding) :
        BaseConcatHolder<MenuAdapter>(binding.root) {
        override fun bind(adapter: MenuAdapter) {
            binding.rvMenu.adapter = adapter
        }
    }
}