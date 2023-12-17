package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityFindIdBinding
import com.example.myapplication.databinding.ActivityFindPasswordBinding
import com.example.myapplication.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class FindPasswordActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.findPasswordButton.setOnClickListener {
            var email = binding.emailEt.text.toString()
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener {
                        result ->
                    if(result.isSuccessful){
                        Toast.makeText(this,"비밀번호 재설정 이메일을 발송하였습니다.",Toast.LENGTH_SHORT).show()
                        val nextIntent = Intent(this, LogInActivity::class.java)
                        startActivity(nextIntent)
                    }
                    else {
                        // 구현 아직 안 됨..
                        val exception = result.exception
                        if (exception is FirebaseAuthInvalidUserException) {
                            Toast.makeText(this, "등록되지 않은 이메일 주소입니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }

        binding.joinUsButton.setOnClickListener {
            Log.d("jooyeong", "move to sign in page")
            val nextIntent = Intent(this, SignInActivity::class.java)
            startActivity(nextIntent)
        }
    }
}