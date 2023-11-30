package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityFindIdBinding
import com.example.myapplication.databinding.ActivityLogInBinding

class FindIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.findIdButton.setOnClickListener {
            Log.d("jooyeong", "find id Button Clicked")
            Toast.makeText(this, "아이디 찾기 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.joinUsButton.setOnClickListener {
            Log.d("jooyeong", "move to sign in page")
            val nextIntent = Intent(this, SignInActivity::class.java)
            startActivity(nextIntent)
        }
    }
}