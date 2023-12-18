package com.example.myproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.example.myproject.databinding.ActivityProfileBinding
import com.google.android.material.navigation.NavigationView

class ProfileActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            binding.toolbar,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)

        binding.navigationView.setNavigationItemSelectedListener(this)

        binding.btnBookmark.setOnClickListener {
           val intent = Intent(this, FavoritePhotospotActivity::class.java)
            startActivity(intent)
        }

        binding.btnModifyinfo.setOnClickListener{
            val intent = Intent(this, EditInfoActivity::class.java)
            startActivity(intent)
        }

        binding.logout.setOnClickListener {
            Toast.makeText(this, "로그아웃이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val eventHandler = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (p1 == DialogInterface.BUTTON_NEGATIVE) {
                    Toast.makeText(applicationContext, "탈퇴되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.unregister.setOnClickListener {
            AlertDialog.Builder(this).run {
                setMessage("정말 탈퇴하시겠습니까?")
                setPositiveButton("아니요", null)
                setNegativeButton("예", eventHandler)
                show()
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 토글 버튼에서 발생하면
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_location -> {
                val intent = Intent(this, LocationPageActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item_feature -> {
                val intent = Intent(this, FeatureActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item_gallery -> {
                val intent = Intent(this, GalleryActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item_pose -> {
                val intent = Intent(this, PoseActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return true
    }
}