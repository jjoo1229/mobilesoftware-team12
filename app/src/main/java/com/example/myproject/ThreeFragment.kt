package com.example.myproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myproject.databinding.FragmentTwoBinding


class ThreeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTwoBinding.inflate(layoutInflater, container, false)
        val datas = mutableListOf<Int>(R.drawable.all1, R.drawable.all2, R.drawable.all3, R.drawable.all4, R.drawable.all5, R.drawable.all6)

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.adapter = MyAdapter(datas)

        return binding.root
    }
}