package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        binding.text.text = auth.currentUser?.email

        binding.moveToLogin.setOnClickListener {
            Log.d("jooyeong","move to log in page")
            val nextIntent = Intent(this, LogInActivity::class.java)
            startActivity(nextIntent)
        }

        binding.moveToSignIn.setOnClickListener {
            Log.d("jooyeong","move to sign in page")
            val nextIntent = Intent(this,SignInActivity::class.java)
            startActivity(nextIntent)
        }
    }
}