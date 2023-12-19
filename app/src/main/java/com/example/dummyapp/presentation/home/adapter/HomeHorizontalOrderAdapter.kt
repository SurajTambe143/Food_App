package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.databinding.ItemFoodDetailsBinding
import com.example.dummyapp.databinding.ItemHomeHorizontalOrderBinding
import com.example.dummyapp.domain.model.HomeOrderFoodDetails

class HomeHorizontalOrderAdapter(private val homeOrderList: List<HomeOrderFoodDetails>) :
    RecyclerView.Adapter<HomeHorizontalOrderAdapter.HomeOrderViewHolder>() {
    class HomeOrderViewHolder(val binding:ItemHomeHorizontalOrderBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOrderViewHolder {
        val binding =
            ItemHomeHorizontalOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HomeOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeOrderList.size
    }

    override fun onBindViewHolder(holder: HomeOrderViewHolder, position: Int) {
        with(holder) {
            with(homeOrderList[position]) {
                binding.txtHorizontalOrder.text=this.title
                binding.rvHomeHrzOrder.let {
                    it.layoutManager =
                        LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
                    it.adapter=HorizontalOrderAdapter(this.rvList)
                }
            }
        }
    }
}