package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.databinding.ItemHomeFoodDetailsVerticalScrollBinding
import com.example.dummyapp.domain.model.OrderFoodDetails

class VerticalOrderAdapter(private val orderlist: List<OrderFoodDetails>, private val onClick:()->Unit): RecyclerView.Adapter<VerticalOrderAdapter.VerticalOrderViewHolder>(){
    class VerticalOrderViewHolder(val binding: ItemHomeFoodDetailsVerticalScrollBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalOrderViewHolder {
        val binding =
            ItemHomeFoodDetailsVerticalScrollBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VerticalOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderlist.size
    }

    override fun onBindViewHolder(holder: VerticalOrderViewHolder, position: Int) {
        with(holder) {
            with(orderlist[position]) {
                binding.itemFoodDetailsImg.load(this.imgOrder)
                binding.itemFoodDetailsTxt.text = this.txtTitle
                binding.chip1Likes.text=this.txtLikes
                binding.chip2Distance.text=this.txtDistance
                binding.chip3Time.text=this.txtTime
                binding.chip4Iqd.text=this.txtIQD
                binding.chip5Ratings.text=this.txtRating
                binding.root.setOnClickListener {
                    onClick.invoke()
                }
            }
        }
    }
}