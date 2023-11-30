package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLogInBinding
import com.example.myapplication.databinding.ActivitySignInBinding

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            Log.d("jooyeong","log in Button Clicked")
            Toast.makeText(this, "로그인되었습니다.", Toast.LENGTH_SHORT).show()
        }
        binding.findIdPage.setOnClickListener {
            Log.d("jooyeong","move to find id page")
            val nextIntent = Intent(this, FindIdActivity::class.java)
            startActivity(nextIntent)
        }
        binding.findPasswordPage.setOnClickListener {
            Log.d("jooyeong","move to find password page")
            val nextIntent = Intent(this, FindPasswordActivity::class.java)
            startActivity(nextIntent)
        }
        binding.joinUsButton.setOnClickListener {
            Log.d("jooyeong","move to sign in page")
            val nextIntent = Intent(this, SignInActivity::class.java)
            startActivity(nextIntent)
        }
    }
}