package com.example.myproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mobilesoftware_kangjihye.feature_harufilm
import com.example.mobilesoftware_kangjihye.feature_life4cut
import com.example.mobilesoftware_kangjihye.feature_monomention
import com.example.mobilesoftware_kangjihye.feature_oldmoon
import com.example.mobilesoftware_kangjihye.feature_photogray
import com.example.mobilesoftware_kangjihye.feature_photoism
import com.example.mobilesoftware_kangjihye.feature_photosignature
import com.example.myproject.LocationPageActivity
import com.example.myproject.R
import com.example.myproject.databinding.ActivityFeatureBinding

class FeatureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeatureBinding
    private lateinit var viewPager: ViewPager2
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(
            this,
            binding.featureDrawerLayout,
            binding.toolbar,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)



        // ViewPager2 찾기
        viewPager = findViewById(R.id.feature_viewpager2)

        // 어댑터 초기화 및 ViewPager2에 설정
        val adapter = YourViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        // 각 버튼에 대한 클릭 리스너 설정
        // 하루 필름
        findViewById<Button>(R.id.btn_feature_haru).setOnClickListener {
            viewPager.currentItem = 0
        }

        // 그믐달 스튜디오
        findViewById<Button>(R.id.btn_feature_oldmoon).setOnClickListener {
            viewPager.currentItem = 1
        }

        // 포토 시그니처
        findViewById<Button>(R.id.btn_feature_signiture).setOnClickListener {
            viewPager.currentItem = 2
        }

        // 인생네컷
        findViewById<Button>(R.id.btn_feature_life).setOnClickListener {
            viewPager.currentItem = 3
        }

        // 모노맨션
        findViewById<Button>(R.id.btn_feature_mono).setOnClickListener {
            viewPager.currentItem = 4
        }

        // 포토이즘
        findViewById<Button>(R.id.btn_feature_photoism).setOnClickListener {
            viewPager.currentItem = 5
        }

        // 포토그레이
        findViewById<Button>(R.id.btn_feature_gray).setOnClickListener {
            viewPager.currentItem = 6
        }

        // 메서드 추가
        fun onImageFeatureClick(view: View) {
            // 현재는 feature 화면으로 이동하도록 되어 있지만, 필요에 따라 수정 가능
            val intent = Intent(this, FeatureActivity::class.java)
            startActivity(intent)
        }

        fun onImageLocationClick(view: View) {
            val intent = Intent(this, LocationPageActivity::class.java)
            startActivity(intent)
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

    private fun updateViewPager(fragment: Fragment) {
        // ViewPager2 어댑터를 통해 프래그먼트를 추가 또는 업데이트
        val adapter = viewPager.adapter as YourViewPagerAdapter
        adapter.addFragment(fragment)
    }

    class YourViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        private val fragments: MutableList<Fragment> = ArrayList()

        init {
            fragments.add(feature_harufilm())
            fragments.add(feature_oldmoon())
            fragments.add(feature_photosignature())
            fragments.add(feature_life4cut())
            fragments.add(feature_monomention())
            fragments.add(feature_photoism())
            fragments.add(feature_photogray())
        }

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment: Fragment) {
            fragments.add(fragment)
            notifyDataSetChanged()
        }

        // 새로운 메서드 추가
        fun setCurrentItem(viewPager2: ViewPager2, position: Int) {
            viewPager2.currentItem = position
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