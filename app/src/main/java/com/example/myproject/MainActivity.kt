package com.example.myproject


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout

import com.example.myproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_white)


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //화면에 보여짐
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main_white, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 토글 버튼에서 발생하면
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.menu_myprofile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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