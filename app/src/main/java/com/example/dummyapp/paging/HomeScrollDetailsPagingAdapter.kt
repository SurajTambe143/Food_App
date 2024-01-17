package com.example.dummyapp.paging

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.databinding.ItemHomeFoodDetailsVerticalScrollBinding
import com.example.dummyapp.domain.model.entities.remote.homescroll.Store
import com.example.dummyapp.utils.gone
import com.example.dummyapp.utils.homeOrderDeliveryTimeConverter
import com.example.dummyapp.utils.homeOrderDistanceConverter

class HomeScrollDetailsPagingAdapter(private val onClick: () -> Unit) :
    PagingDataAdapter<Store, HomeScrollDetailsPagingAdapter.HomeScrollDetailsViewHolder>(COMPARATOR) {

    class HomeScrollDetailsViewHolder(val binding: ItemHomeFoodDetailsVerticalScrollBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScrollDetailsViewHolder {
        val binding = ItemHomeFoodDetailsVerticalScrollBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeScrollDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeScrollDetailsViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                binding.itemFoodDetailsImg.load(com.example.dummyapp.utils.Constants.BASE_IMAGE_URL + this?.image_url) {
                    placeholder(binding.root.context.getDrawable(com.example.dummyapp.R.drawable.img_placeholder))
                    // Other options and transformations can be applied here
                }
                binding.itemFoodDetailsTxt.text = this?.name
                binding.chip1Likes.text = this?.user_rate.toString()
                binding.chip2Distance.text =
                    homeOrderDistanceConverter(binding.root.context, this?.distance)
                binding.chip3Time.text = homeOrderDeliveryTimeConverter(
                    binding.root.context, this?.delivery_time, this?.delivery_time_max
                )
                binding.chip4Iqd.text = this?.user_rate_count.toString()
                binding.chip5Ratings.text = this?.user_rate.toString()
                binding.root.setOnClickListener {
                    onClick.invoke()
                }
                binding.txtOffersOnSelectedItem.text = (if (this?.offer == "") {
                    binding.txtOffersOnSelectedItem.gone()
                    binding.lowerTriangleOrange.gone()
                } else this?.offer).toString()
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Store>() {
            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem == newItem
            }
        }
    }
}