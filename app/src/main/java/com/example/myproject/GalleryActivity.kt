package com.example.myproject

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myproject.databinding.ActivityGalleryBinding
import com.example.myproject.databinding.CustomDialogBinding

import com.google.android.material.navigation.NavigationView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class GalleryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var filePath: String
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
        val galleryAdapter = GalleryAdapter()
        binding.gallRecyclerview.adapter = galleryAdapter

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
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
                                year: Int,
                                month: Int,
                                day: Int
                            ) {

                                Log.d("soni", "year : $year, month : ${month + 1}, dayOfMonth : $day")
                                galleryAdapter.addImage(bitmap)
                                galleryAdapter.addDate(year, month , day)
                            }
                        }, 2023, 12, 2).show()
                    }
                    dialogBinding.choicePhotospot.setOnClickListener{
                        val items = arrayOf<String>("• 그믐달 스튜디오", "• 모노맨션", "• 인생네컷", "• 포토그레이", "• 포토시그니처", "• 포토이즘", "• 하루필름", "• 기타")
                        AlertDialog.Builder(this).run {
                            setTitle("사진관 선택")
                            setItems(items,  object: DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    Log.d("soni", "${items[which]}가 선택되었습니다.")

                                }
                            })
                            show()
                        }
                    }
                    dialog.show()
                    } ?: let {
                        Log.d("kkang", "bitmap null")
                    }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                galleryAdapter.addImage(bitmap)
            }
        }
        binding.navigationView.setNavigationItemSelectedListener(this)
        binding.gallRecyclerview.layoutManager = GridLayoutManager(this, 2)



        binding.sortingBtn.setOnClickListener{view ->
            val popupsortingbtn = PopupMenu(this,view)
            popupsortingbtn.menuInflater.inflate(R.menu.menu_sort, popupsortingbtn.menu)
            popupsortingbtn.show()
        }

        binding.datesortBtn.setOnClickListener {
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

        binding.photospotBtn.setOnClickListener {view ->
            val popupphotospotbtn = PopupMenu(this, view)
            popupphotospotbtn.menuInflater.inflate(R.menu.menu_photospot, popupphotospotbtn.menu)
            popupphotospotbtn.show()
        }

        binding.fab.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.menu_fab, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.fab_gallery -> {
                        Log.d("soni", "go to gallery")

                        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        intent.type = "image/*"
                        requestGalleryLauncher.launch(intent)
                        true
                    }


                    R.id.fab_camera -> {
                        Log.d("soni", "go to camera")
                        val timeStamp: String =
                            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                        galleryAdapter.addDate(
                            timeStamp.substring(0, 4).toInt(),
                            timeStamp.substring(4, 6).toInt(),
                            timeStamp.substring(6, 8).toInt()
                        )
                        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                        val file = File.createTempFile(
                            "JPEG_${timeStamp}_",
                            ".jpg",
                            storageDir
                        )
                        filePath = file.absolutePath
                        val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.example.myproject.fileprovider",
                            file
                        )
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        requestCameraFileLauncher.launch(intent)
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
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}