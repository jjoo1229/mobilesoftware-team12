package com.example.myproject


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

import com.example.myproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuToggle = binding.menuToggle
        val drawerLayout: DrawerLayout = binding.drawerLayout

        menuToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                drawerLayout.openDrawer(binding.drawerMenu)
            } else {
                drawerLayout.closeDrawer(binding.drawerMenu)
            }
        }
    }

    // 메서드 추가
    fun onLinearLocationClick(view: View) {
        Log.d("MainActivity", "onImageLocationClick called")
        val intent = Intent(this, LocationPageActivity::class.java)
        startActivity(intent)
    }

    fun onLinearFeatureClick(view: View) {
        Log.d("MainActivity", "onImageFeatureClick called")
        val intent = Intent(this, FeatureActivity::class.java)
        startActivity(intent)
    }

    fun onLinearGalleryClick(view: View) {
        Log.d("MainActivity", "onLinearGalleryClick called")
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }

    fun onImagePoseClick(view: View) {
        Log.d("MainActivity", "onImagePoseClick called")
        val intent = Intent(this, PoseActivity::class.java)
        startActivity(intent)
    }

}