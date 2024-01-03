package com.example.dummyapp.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ItemFoodDetailsBinding
import com.example.dummyapp.databinding.ItemHomeHorizontalOrderBinding
import com.example.dummyapp.domain.model.HomeOrderFoodDetails
import com.example.dummyapp.domain.model.MenuItem

class HomeHorizontalOrderAdapter(private val onClick: () -> Unit) :
    RecyclerView.Adapter<HomeHorizontalOrderAdapter.HomeOrderViewHolder>() {

    private var homeOrderList: List<HomeOrderFoodDetails> =
        emptyList<HomeOrderFoodDetails>().toMutableList()
    private var horizontalOrderAdapter: HorizontalOrderAdapter =
        HorizontalOrderAdapter { onClick.invoke() }

    private lateinit var snapHelper: LinearSnapHelper

    class HomeOrderViewHolder(val binding: ItemHomeHorizontalOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOrderViewHolder {
        val binding =
            ItemHomeHorizontalOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return HomeOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeOrderList.size
    }


    override fun onBindViewHolder(holder: HomeOrderViewHolder, position: Int) {
        snapHelper = LinearSnapHelper()
        with(holder) {
            with(homeOrderList[position]) {
                if (position == 0) {
                    binding.root.setBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.white
                        )
                    )
                }
                binding.txtHorizontalOrder.text = this.title
                binding.rvHomeHrzOrder.let {
                    it.layoutManager =
                        LinearLayoutManager(
                            holder.itemView.context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    it.adapter = horizontalOrderAdapter
                    horizontalOrderAdapter.updateList(this.rvList)
                }
            }
            snapHelper.attachToRecyclerView(binding.rvHomeHrzOrder)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<HomeOrderFoodDetails>) {
        homeOrderList = list
        notifyDataSetChanged()
    }

}