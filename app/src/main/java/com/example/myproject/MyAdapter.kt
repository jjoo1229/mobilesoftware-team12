package com.example.myproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.ItemRecyclerviewBinding

class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val images2:MutableList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = images2.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemImage.setImageResource(images2[position])
    }
}