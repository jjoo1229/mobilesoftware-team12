package com.example.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myproject.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    class MyFragmentPagerAdapter(activity: FragmentActivity):
        FragmentStateAdapter(activity) {
        val fragments: List<Fragment>
        init{
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

        toggle = ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        //viewpager2 코드 작성
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)
        //tablayout 코드 작성
        val tabTitles = listOf("1인", "2인", "3인 이상")
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //화면에 보여짐
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        //서치뷰 입력된 검색어 처리
        val menuItem = menu?.findItem(R.id.menu_item_search)
        val searchView = menuItem?.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //검색어 입력 완료시 처리
                Log.d("soni", "query: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어 입력시 처리
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        when(item.itemId) {
            R.id.menu_item_setting -> {
                Log.d("soni", "setting")
            }
            R.id.menu_item_help -> {
                Log.d("soni", "help")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}