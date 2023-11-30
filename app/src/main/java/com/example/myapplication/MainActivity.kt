package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivitySignInBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.duplicateCheckButton.setOnClickListener {
            Log.d("jooyeong","duplicate Button Clicked")
            Toast.makeText(this, "중복되는 아이디입니다.", Toast.LENGTH_SHORT).show()
        }

        binding.signInButton.setOnClickListener {
            Log.d("jooyeong","sign In Button Clicked")
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            val nextIntent = Intent(this, LogInActivity::class.java)
            startActivity(nextIntent)
        }
    }
}