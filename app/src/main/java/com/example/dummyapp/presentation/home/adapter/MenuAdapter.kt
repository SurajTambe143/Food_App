package com.example.dummyapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.dummyapp.databinding.ItemHomeMenuBinding
import com.example.dummyapp.domain.model.MenuItem

class MenuAdapter() :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private var menuList: List<MenuItem> = emptyList<MenuItem>().toMutableList()

    class MenuViewHolder(val binding: ItemHomeMenuBinding) : ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding =
            ItemHomeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        with(holder) {
            with(menuList[position]) {
                binding.imgMenu.load(this.img)
                binding.txtMenu.text = this.txt
            }
        }
    }

    fun updateList(list: List<MenuItem>) {
        menuList = list
        notifyDataSetChanged()
    }

}