package com.example.myproject

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTwoBinding.inflate(layoutInflater, container, false)
        val datas = mutableListOf<Int>(R.drawable.two1, R.drawable.two2, R.drawable.two3, R.drawable.two4, R.drawable.two5, R.drawable.two6)

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.adapter = MyAdapter(datas)

        return binding.root
    }
}