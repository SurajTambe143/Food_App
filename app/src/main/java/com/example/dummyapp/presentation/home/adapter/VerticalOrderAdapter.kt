package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ItemHomeFoodDetailsVerticalScrollBinding
import com.example.dummyapp.domain.model.OrderFoodDetails
import com.example.dummyapp.domain.model.entities.remote.homescroll.Store
import com.example.dummyapp.utils.Constants.BASE_IMAGE_URL
import com.example.dummyapp.utils.gone
import com.example.dummyapp.utils.homeOrderDeliveryTimeConverter
import com.example.dummyapp.utils.homeOrderDistanceConverter

class VerticalOrderAdapter(
    private val onClick: () -> Unit
) : RecyclerView.Adapter<VerticalOrderAdapter.VerticalOrderViewHolder>() {

    private var orderList: List<Store?>? = emptyList<Store>().toMutableList()

    class VerticalOrderViewHolder(val binding: ItemHomeFoodDetailsVerticalScrollBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalOrderViewHolder {
        val binding =
            ItemHomeFoodDetailsVerticalScrollBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return VerticalOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderList?.size ?: 0
    }

    override fun onBindViewHolder(holder: VerticalOrderViewHolder, position: Int) {
        with(holder) {
            with(orderList?.get(position)) {
                binding.itemFoodDetailsImg.load(BASE_IMAGE_URL + this?.image_url)
                binding.itemFoodDetailsTxt.text = this?.name
                binding.chip1Likes.text = this?.user_rate.toString()
                binding.chip2Distance.text = homeOrderDistanceConverter(this?.distance)
                binding.chip3Time.text = homeOrderDeliveryTimeConverter(this?.delivery_time,this?.delivery_time_max)
                binding.chip4Iqd.text = this?.user_rate_count.toString()
                binding.chip5Ratings.text = this?.user_rate.toString()
                binding.root.setOnClickListener {
                    onClick.invoke()
                }
                binding.txtOffersOnSelectedItem.text = (if (this?.offer == "") {
                    binding.txtOffersOnSelectedItem.gone()
                    binding.lowerTriangleOrange.gone()
                } else this?.offer
                        ).toString()
//                binding.root.startAnimation(
//                    AnimationUtils.loadAnimation(
//                        holder.itemView.context,
//                        R.anim.home_rv_vertical_anim
//                    )
//                )
            }
        }
    }

    fun updateList(list: List<Store?>?) {
        orderList = list
        notifyDataSetChanged()
    }
}