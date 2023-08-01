package com.example.lets_trip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JoinActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        dbHelper = DBHelper(this)

        val usernameEditText = findViewById<EditText>(R.id.edit_id2)
        val passwordEditText = findViewById<EditText>(R.id.edit_pw2)
        val signUpButton = findViewById<Button>(R.id.btn_register2)
        val loginButton = findViewById<Button>(R.id.btn_login2)

        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                // 회원가입 정보를 DB에 저장
                val insertedRowId = dbHelper.insertUser(username, password)
                if (insertedRowId != -1L) {
                    showToast("회원가입 성공")
                    finish()
                } else {
                    showToast("회원가입 실패")
                }
            } else {
                showToast("사용자 이름과 비밀번호를 입력하세요.")
            }
        }

        fun moveToAnotherPage(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // 함수호출
        loginButton.setOnClickListener{
            moveToAnotherPage()
        }
    }

    private fun showToast(message: String) {
        // 기본 Toast 사용
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}