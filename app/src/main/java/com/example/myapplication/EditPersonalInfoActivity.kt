package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityEditPersonalInfoBinding
import com.example.myapplication.databinding.ActivityFindPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class EditPersonalInfoActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEditPersonalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        var email = auth.currentUser?.email
        binding.emailEt.setText(email)

        binding.editButton.setOnClickListener {
            Log.d("jooyeong", "edit Button Clicked")
            Toast.makeText(this, "변경하기 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}