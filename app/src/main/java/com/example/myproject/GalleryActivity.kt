package com.example.myproject

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myproject.databinding.ActivityGalleryBinding
import com.example.myproject.databinding.ActivityMainBinding
import com.example.myproject.databinding.ActivityProfileBinding
import com.example.myproject.databinding.CustomDialogBinding
import com.example.myproject.databinding.GalleryRecyclerviewBinding

import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Date

class GalleryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGalleryBinding.inflate(layoutInflater)
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
        val datas = mutableListOf<Int>(R.drawable.one1, R.drawable.one2, R.drawable.one3, R.drawable.one4, R.drawable.one5, R.drawable.one6)
        binding.gallRecyclerview.layoutManager = GridLayoutManager(this, 2)
        binding.gallRecyclerview.adapter = GalleryAdapter(datas)



        binding.fab.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.menu_fab, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.fab_gallery -> {
                        Log.d("soni", "go to gallery")
                        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
                        val dialog = AlertDialog.Builder(this).create()

                        dialog.setView(dialogBinding.root)
                        dialog.setCancelable(false)

                        dialogBinding.closebtn.setOnClickListener {
                            dialog.dismiss()
                        }

                        dialogBinding.calendarbtn.setOnClickListener {
                            Log.d("soni", "calendar button")
                            DatePickerDialog(this, object:DatePickerDialog.OnDateSetListener {
                                override fun onDateSet(
                                    p0: DatePicker?,
                                    p1: Int,
                                    p2: Int,
                                    p3: Int
                                ) {
                                    Log.d("soni", "year : $p1, month : ${p2 + 1}, dayOfMonth : $p3")
                                }
                            }, 2023, 12, 2).show()
                        }
                        dialogBinding.choicePhotospot.setOnClickListener{
                            val items = arrayOf<String>("그믐달 스튜디오", "모노맨션", "인생네컷", "포토그레이", "포토시그니처", "포토이즘", "하루필름", "기타")
                            AlertDialog.Builder(this).run {
                                setTitle("사진관 선택")
                                setSingleChoiceItems(items, 1, object: DialogInterface.OnClickListener{
                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        Log.d("soni", "${items[which]}가 선택되었습니다.")
                                    }
                                })
                                show()
                            }
                        }
                        dialog.show()
                        true
                    }
                    R.id.fab_camera -> {
                        Log.d("soni", "go to camera")
                        true
                    }
                    // Add more cases for other menu items if needed
                    else -> false
                }
            }
            popup.show()
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