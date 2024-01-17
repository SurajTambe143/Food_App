package com.example.dummyapp.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ItemOffersBinding
import com.example.dummyapp.domain.model.entities.remote.homemain.Banner
import com.example.dummyapp.utils.Constants.BASE_IMAGE_URL

class HomeOffersAdapter : RecyclerView.Adapter<HomeOffersAdapter.HomeOffersViewHolder>() {
    private var items: List<Banner>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOffersViewHolder {
        val binding = ItemOffersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeOffersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeOffersViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int = items!!.size

    inner class HomeOffersViewHolder(private val binding: ItemOffersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Banner?) {
            binding.imageView.load(BASE_IMAGE_URL +item?.image_url){
                placeholder(binding.root.context.getDrawable(R.drawable.img_placeholder))
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Banner>?) {
        this.items = list
        notifyDataSetChanged()
    }
}