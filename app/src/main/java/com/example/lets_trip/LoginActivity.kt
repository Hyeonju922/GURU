package com.example.lets_trip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DBHelper(this)

        val usernameEditText = findViewById<EditText>(R.id.edit_id)
        val passwordEditText = findViewById<EditText>(R.id.edit_pw)
        val loginButton = findViewById<Button>(R.id.btn_login)
        val registerButton = findViewById<Button>(R.id.btn_register)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (isValidCredentials(username, password)) {
                // 로그인 성공 처리
                showToast("로그인 성공")
                val intent2 = Intent(this, MainActivity::class.java)
                startActivity(intent2)
                // 여기에 원하는 화면으로 이동하는 코드를 추가할 수 있습니다.
            } else {
                showToast("유효하지 않은 사용자 정보입니다.")
            }
        }

        fun moveToAnotherPage(){
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        // 함수호출
        registerButton.setOnClickListener{
            moveToAnotherPage()
        }
    }

    private fun showToast(message: String) {
        // 기본 Toast 사용
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // SQLite를 사용하여 사용자 정보를 조회하고 비교하는 코드를 추가합니다.
        // 여기서는 간단한 예시로 DB에 저장된 고정된 사용자 정보를 사용합니다.
        val db = dbHelper.readableDatabase
        val selectionArgs = arrayOf(username, password)
        val query = "SELECT * FROM ${DBHelper.TABLE_NAME} WHERE ${DBHelper.COLUMN_USERNAME} = ? AND ${DBHelper.COLUMN_PASSWORD} = ?"
        val cursor = db.rawQuery(query, selectionArgs)
        val isValid = cursor.moveToFirst()
        cursor.close()
        return isValid
    }
}
