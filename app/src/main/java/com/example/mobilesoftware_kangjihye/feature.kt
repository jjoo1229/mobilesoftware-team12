package com.example.mobilesoftware_kangjihye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mobilesoftware_kangjihye.databinding.ActivityFeatureBinding

class feature : AppCompatActivity() {

    private lateinit var binding: ActivityFeatureBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeatureBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val menuToggle = binding.featureMenuToggle
        val drawerLayout: DrawerLayout = binding.featureDrawerLayout

        menuToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                drawerLayout.openDrawer(binding.featureDrawerMenu)
                drawerLayout.visibility = View.VISIBLE
            } else {
                drawerLayout.closeDrawer(binding.featureDrawerMenu)
                drawerLayout.visibility = View.INVISIBLE
            }
        }

        // ViewPager2 찾기
        viewPager = findViewById(R.id.feature_viewpager2)

        // 어댑터 초기화 및 ViewPager2에 설정
        val adapter = YourViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        // 각 버튼에 대한 클릭 리스너 설정
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
            val intent = Intent(this, feature::class.java)
            startActivity(intent)
        }

        fun onImageLocationClick(view: View) {
            val intent = Intent(this, location_page::class.java)
            startActivity(intent)
        }

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
            fragments.add(feature_photosigniture())
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
    fun onImageFeatureClick(view: View) {
        val intent = Intent(this, feature::class.java)
        startActivity(intent)
    }

    fun onImageLocationClick(view: View) {
        val intent = Intent(this, location_page::class.java)
        startActivity(intent)
    }

}