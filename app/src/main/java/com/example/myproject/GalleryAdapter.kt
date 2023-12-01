package com.example.myproject

import android.app.ProgressDialog.show
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.GalleryRecyclerviewBinding
import com.example.myproject.databinding.ItemRecyclerviewBinding

class GalleryViewHolder(val binding: GalleryRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class GalleryAdapter(val images2:MutableList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = images2.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = GalleryViewHolder(GalleryRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as GalleryViewHolder).binding
        binding.itemImage.setImageResource(images2[position])


        binding.itemTrashbtn.setOnClickListener{
            AlertDialog.Builder(binding.root.context).run {
                setMessage("정말 삭제하시겠습니까?")
                setPositiveButton("아니요", null)
                setNegativeButton("예", null)
                show()
            }
        }

    }

}