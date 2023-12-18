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
import com.example.myproject.databinding.ActivityLocationPageBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class LocationPageActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityLocationPageBinding
    private var mMap: GoogleMap? = null
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(
            this,
            binding.locationDrawerLayout,
            binding.toolbar,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)


        // 버튼 클릭 시 해당 위치로 스크롤

        binding.button11.setOnClickListener {
            scrollToView(binding.locationHarufilm)
        }

        binding.button12.setOnClickListener {
            scrollToView(binding.locationOldmoon)
        }

        binding.button21.setOnClickListener {
            scrollToView(binding.locationPhotosignature)
        }

        binding.button22.setOnClickListener {
            scrollToView(binding.locationLife4cut)
        }

        binding.button23.setOnClickListener {
            scrollToView(binding.locationMonomention)
        }

        binding.button31.setOnClickListener {
            scrollToView(binding.locationPhotoism)
        }

        binding.button32.setOnClickListener {
            scrollToView(binding.locationPhotogray)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val SEOUL = LatLng(37.556, 126.97)

        val markerOptions = MarkerOptions()
        markerOptions.position(SEOUL)
        markerOptions.title("서울")
        markerOptions.snippet("한국 수도")

        mMap?.addMarker(markerOptions)
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10f))
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

    private fun scrollToView(view: View) {
        binding.scrollView.post {
            binding.scrollView.smoothScrollTo(0, view.top)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //화면에 보여짐
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
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
}
