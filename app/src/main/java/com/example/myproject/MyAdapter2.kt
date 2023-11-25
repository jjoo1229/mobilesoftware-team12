package com.example.myproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.ItemRecyclerview2Binding
import com.example.myproject.databinding.ItemRecyclerviewBinding

class MyViewHolder2(val binding: ItemRecyclerview2Binding): RecyclerView.ViewHolder(binding.root)

class MyAdapter2( val images:MutableList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = MyViewHolder2(ItemRecyclerview2Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder2).binding

        binding.itemImage2.setImageResource(images[position])
        //binding.itemData1.text = datas1[position]
        //binding.itemData2.text = datas2[position]
    }
}