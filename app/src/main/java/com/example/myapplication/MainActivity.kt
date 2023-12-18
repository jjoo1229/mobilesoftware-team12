package com.example.myapplication


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import android.app.AlertDialog


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
            val nextIntent = Intent(this, SignInActivity::class.java)
            startActivity(nextIntent)
        }

        binding.moveToEditPersonalInfo.setOnClickListener {
            Log.d("jooyeong","move to 개인정보 수정 page")
            val nextIntent = Intent(this, EditPersonalInfoActivity::class.java)
            startActivity(nextIntent)
        }

        // 로그아웃
        binding.logOut.setOnClickListener {
            auth.signOut()
            var nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
            finish()
        }

        binding.delete.setOnClickListener {
            this.confirmDeleteUser()
        }
    }

    // 탈퇴
    fun confirmDeleteUser() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("정말 탈퇴하시겠습니까?")
            .setCancelable(false)
            .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, id ->
                // 사용자가 확인을 누른 경우
                deleteUser() // 사용자 탈퇴 함수 호출
            })
            .setNegativeButton("취소", DialogInterface.OnClickListener { dialog, id ->
                // 사용자가 취소를 누른 경우
                dialog.cancel() // 다이얼로그 닫기
            })

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    fun deleteUser() {
        val user = auth.currentUser

        user?.delete()
            ?.addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val nextIntent = Intent(this, MainActivity::class.java)
                    startActivity(nextIntent)
                    finish()
                } else {
                    val exception = result.exception
                    Log.e("TAG", "탈퇴 실패: ${exception?.message}")
                    Toast.makeText(this, "탈퇴에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}