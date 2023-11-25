package com.example.myproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.databinding.FragmentOneBinding
import com.example.myproject.databinding.FragmentTwoBinding

class OneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOneBinding.inflate(layoutInflater, container, false)
        val images = mutableListOf<Int>(R.drawable.one1, R.drawable.one2, R.drawable.one3, R.drawable.one4, R.drawable.one5, R.drawable.one6)

        binding.recyclerView2.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView2.adapter = MyAdapter2(images)

        return binding.root
    }
}