package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.databinding.ItemOffersBinding
import com.example.dummyapp.domain.model.OffersItem

class HomeOffersAdapter : RecyclerView.Adapter<HomeOffersAdapter.HomeOffersViewHolder>() {
    private var items: List<OffersItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOffersViewHolder {
        val binding = ItemOffersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeOffersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeOffersViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class HomeOffersViewHolder(private val binding: ItemOffersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OffersItem) {
            binding.imageView.load(item.img)
        }
    }

    fun setItem(list: List<OffersItem>) {
        this.items = list
        notifyDataSetChanged()
    }
}