package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLogInBinding
import com.example.myapplication.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {

                var email = binding.emailEt.text.toString()
                var password = binding.pwdEt.text.toString()
                auth.signInWithEmailAndPassword(email,password) // 회원 가입
                    .addOnCompleteListener {
                            result ->
                        if(result.isSuccessful){
                            Log.d("jooyeong","로그인 성공")
                            Toast.makeText(this,"로그인이 완료되었습니다.",Toast.LENGTH_SHORT).show()
                            val user = auth.currentUser
                            if(auth.currentUser!=null){
                                var intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                        else {
                            Toast.makeText(this,"이메일 혹은 비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }
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