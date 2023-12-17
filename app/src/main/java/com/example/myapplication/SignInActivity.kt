package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivitySignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class SignInActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.signInButton.setOnClickListener {

            var email = binding.signInEmail.text.toString()
            var password = binding.signInPassword.text.toString()
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                        result ->
                    if(result.isSuccessful){
                        Toast.makeText(this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this,"오류가 발생했습니다.",Toast.LENGTH_SHORT).show()
                    }

                }
        }

//        binding.duplicateCheckButton.setOnClickListener {
//            Log.d("jooyeong","duplicate Button Clicked")
//            Toast.makeText(this, "중복되는 아이디입니다.", Toast.LENGTH_SHORT).show()
//        }


    }
}