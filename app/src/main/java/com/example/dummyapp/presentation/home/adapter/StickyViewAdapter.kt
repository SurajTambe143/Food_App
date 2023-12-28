package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.databinding.ItemHomeStickyRvBinding
import com.example.dummyapp.domain.model.StickyItem

class StickyViewAdapter(private val stickyList: List<StickyItem>) :
    RecyclerView.Adapter<StickyViewAdapter.StickyViewHolder>() {

    class StickyViewHolder(val binding: ItemHomeStickyRvBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickyViewHolder {
        val binding =
            ItemHomeStickyRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return StickyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stickyList.size
    }

    override fun onBindViewHolder(holder: StickyViewHolder, position: Int) {
        with(holder) {
            with(stickyList[position]) {
                binding.txtSticky.text = this.txt
            }
        }
    }

}