package com.example.mobilesoftware_kangjihye


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mobilesoftware_kangjihye.databinding.ActivityMainBinding


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
    fun onImageLocationClick(view: View) {
        Log.d("MainActivity", "onImageLocationClick called")
        val intent = Intent(this, location_page::class.java)
        startActivity(intent)
    }

    fun onImageFeatureClick(view: View) {
        Log.d("MainActivity", "onImageFeatureClick called")
        val intent = Intent(this, feature::class.java)
        startActivity(intent)
    }

}
