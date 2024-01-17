package com.example.dummyapp.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.databinding.ItemLoaderPagingBinding

class HomeScrollDetailsLoaderAdapter : LoadStateAdapter<HomeScrollDetailsLoaderAdapter.HomeScrollDetailsLoaderViewHolder>() {
    class HomeScrollDetailsLoaderViewHolder(val binding: ItemLoaderPagingBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(loadState: LoadState){
                binding.pagerLoader.isVisible = loadState is LoadState.Loading
            }
        }

    override fun onBindViewHolder(holder: HomeScrollDetailsLoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): HomeScrollDetailsLoaderViewHolder {
        val binding = ItemLoaderPagingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeScrollDetailsLoaderViewHolder(binding)
    }


}