package com.example.myproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myproject.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle

    class MyFragmentPagerAdapter(activity: FragmentActivity) :
        FragmentStateAdapter(activity) {
        val fragments: List<Fragment>

        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
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

        //viewpager2 코드 작성
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)
        //tablayout 코드 작성
        val tabTitles = listOf("1인", "2인", "3인 이상")
        val tabIcons = listOf(R.drawable.img, R.drawable.img_1, R.drawable.img_2)
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = tabTitles[position]
            tab.setIcon(tabIcons[position])
        }.attach()

        binding.navigationView.setNavigationItemSelectedListener(this)

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
                Log.d("soni", "go to my profile")
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_gallery -> {
                val intent = Intent(this, GalleryActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item_pose -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return true
    }
}