package com.example.myproject

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.GalleryRecyclerviewBinding
import java.util.Calendar

class GalleryViewHolder(val binding: GalleryRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class GalleryAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val imagesList = mutableListOf<Bitmap>()
    private val selectedDatesList = mutableListOf<Calendar>()


    fun addImage(bitmap: Bitmap) {
        imagesList.add(bitmap)
        notifyItemInserted(imagesList.size - 1)
    }

    fun addDate(year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
        selectedDatesList.add(selectedDate)
        notifyItemInserted(selectedDatesList.size - 1)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = GalleryViewHolder(
        GalleryRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as GalleryViewHolder).binding
        binding.itemImage.setImageBitmap(imagesList[position])
        if(selectedDatesList.size > 0)
        {
            val year = selectedDatesList[position].get(Calendar.YEAR)
            val month = selectedDatesList[position].get(Calendar.MONTH) + 1
            val dayOfMonth = selectedDatesList[position].get(Calendar.DAY_OF_MONTH)


            binding.itemDate.setText("$year.$month.$dayOfMonth")
        }

        binding.itemTrashbtn.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context).run {
                setMessage("정말 삭제하시겠습니까?")
                setPositiveButton("아니요", null)
                setNegativeButton("예") { _, _ ->
                    imagesList.removeAt(position)
                    selectedDatesList.removeAt(position)
                    notifyItemRemoved(position)
                }
                show()
            }
        }
    }
    override fun getItemCount(): Int = imagesList.size
}
