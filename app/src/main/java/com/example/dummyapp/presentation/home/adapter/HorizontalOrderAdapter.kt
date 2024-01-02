package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ItemFoodDetailsBinding
import com.example.dummyapp.domain.model.HomeOrderFoodDetails
import com.example.dummyapp.domain.model.OrderFoodDetails

class HorizontalOrderAdapter(
    private val onClick: () -> Unit
) : RecyclerView.Adapter<HorizontalOrderAdapter.HorizontalOrderViewHolder>() {

    private var orderlist: List<OrderFoodDetails> = emptyList<OrderFoodDetails>().toMutableList()

    class HorizontalOrderViewHolder(val binding: ItemFoodDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalOrderViewHolder {
        val binding =
            ItemFoodDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HorizontalOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderlist.size
    }

    override fun onBindViewHolder(holder: HorizontalOrderViewHolder, position: Int) {
        with(holder) {
            if (position == 0) {
                val marginValue =
                    holder.itemView.resources.getDimensionPixelSize(R.dimen.margin_left_value)
                val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.leftMargin = marginValue
                binding.root.layoutParams = layoutParams
            }
            if (position == itemCount) {
                val marginValue =
                    holder.itemView.resources.getDimensionPixelSize(R.dimen.margin_right_value)
                val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.rightMargin = marginValue
                binding.root.layoutParams = layoutParams
            }
            with(orderlist[position]) {
                binding.itemFoodDetailsImg.load(this.imgOrder)
                binding.itemFoodDetailsTxt.text = this.txtTitle
                binding.chip1Likes.text = this.txtLikes
                binding.chip2Distance.text = this.txtDistance
                binding.chip3Time.text = this.txtTime
                binding.chip4Iqd.text = this.txtIQD
                binding.chip5Ratings.text = this.txtRating
                binding.root.setOnClickListener { onClick.invoke() }
                binding.root.startAnimation(
                    AnimationUtils.loadAnimation(
                        holder.itemView.context,
                        R.anim.home_rv_horizontal_anim
                    )
                )
            }
        }
    }
    fun updateList(list: List<OrderFoodDetails>) {
        orderlist = list
        notifyDataSetChanged()
    }
}