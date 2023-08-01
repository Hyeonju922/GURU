package com.example.lets_trip

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var location1 : EditText
    lateinit var departure1 : TextView
    lateinit var arrival1 : TextView
    lateinit var num1 : EditText
    lateinit var name1 : EditText
    lateinit var mPickTimeBtn: ImageButton
    lateinit var textView: TextView
    lateinit var mPickTimeBtn2: ImageButton
    lateinit var textView2: TextView
    lateinit var moveButton: Button
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        location1 = findViewById(R.id.locationText)
        departure1 = findViewById(R.id.dateTv)
        arrival1 = findViewById(R.id.dateTv2)
        num1 = findViewById(R.id.num)
        name1 = findViewById(R.id.name)
        mPickTimeBtn = findViewById<ImageButton>(R.id.pickDateBtn)
        textView     = findViewById<TextView>(R.id.dateTv)
        mPickTimeBtn2 = findViewById<ImageButton>(R.id.pickDateBtn2)
        textView2     = findViewById<TextView>(R.id.dateTv2)
        moveButton = findViewById<Button>(R.id.moveButton)
        dbHelper = DBHelper(this)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                textView.setText("" + year + "년 " + month + "월 " + dayOfMonth + "일 ")
            }, year, month, day)
            dpd.show()

        }

        mPickTimeBtn2.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                textView2.setText("" + year + "년 " + month + "월 " + dayOfMonth + "일 ")
            }, year, month, day)
            dpd.show()

        }

        // 페이지 이동
        fun moveToAnotherPage(){
            val intent = Intent(this, PlanActivity::class.java)
            startActivity(intent)
        }

        val location = location1.text.toString()
        val departure = departure1.text.toString()
        val arrival = arrival1.text.toString()
        val num = num1.text.toString()
        val name = name1.text.toString()

        // 함수호출
        moveButton.setOnClickListener{
            dbHelper.insertPlan(location, departure, arrival, num, name)
            moveToAnotherPage()
        }
    }

}