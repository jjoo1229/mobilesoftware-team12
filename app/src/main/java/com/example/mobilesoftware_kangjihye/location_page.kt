package com.example.mobilesoftware_kangjihye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mobilesoftware_kangjihye.databinding.ActivityLocationPageBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class location_page : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityLocationPageBinding
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuToggle = binding.locationMenuToggle
        val drawerLayout: DrawerLayout = binding.locationDrawerLayout


        menuToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                drawerLayout.openDrawer(binding.locationDrawerMenu)
                drawerLayout.visibility = View.VISIBLE
            } else {
                drawerLayout.closeDrawer(binding.locationDrawerMenu)
                drawerLayout.visibility = View.INVISIBLE
            }
        }

        // 버튼 클릭 시 해당 위치로 스크롤

        binding.button11.setOnClickListener {
            scrollToView(binding.locationHarufilm)
        }

        binding.button12.setOnClickListener {
            scrollToView(binding.locationOldmoon)
        }

        binding.button21.setOnClickListener {
            scrollToView(binding.locationPhotosigniture)
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
    fun onImageFeatureClick(view: View) {
        val intent = Intent(this, feature::class.java)
        startActivity(intent)
    }

    fun onImageLocationClick(view: View) {
        // 현재 액티비티이므로 아무 동작을 하지 않아도 됩니다.
    }

    private fun scrollToView(view: View) {
        binding.scrollView.post {
            binding.scrollView.smoothScrollTo(0, view.top)
        }
    }
}
