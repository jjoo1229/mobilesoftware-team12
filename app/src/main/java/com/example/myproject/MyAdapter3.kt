package com.example.myproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.ItemRecyclerview3Binding


class MyViewHolder3(val binding: ItemRecyclerview3Binding): RecyclerView.ViewHolder(binding.root)

class MyAdapter3( val images:MutableList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = MyViewHolder3(ItemRecyclerview3Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder3).binding

        binding.itemImage3.setImageResource(images[position])
    }
}