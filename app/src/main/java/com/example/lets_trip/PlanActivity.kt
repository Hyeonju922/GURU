package com.example.lets_trip

import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import com.example.lets_trip.databinding.ActivityMainBinding

class PlanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)

        setUpSpinner1()

        val btnShowTimePicker1 = findViewById<ImageButton>(R.id.timepicker1)
        val selectedTimeTextView1 = findViewById<TextView>(R.id.timeshow1)
        val btnShowTimePicker2 = findViewById<ImageButton>(R.id.timepicker2)
        val selectedTimeTextView2 = findViewById<TextView>(R.id.timeshow2)

        btnShowTimePicker1.setOnClickListener {
            showTimePickerDialog(selectedTimeTextView1)
        }

        btnShowTimePicker2.setOnClickListener {
            showTimePickerDialog(selectedTimeTextView2)
        }
    }

    fun setUpSpinner1(){
        val transport = resources.getStringArray(R.array.transport)
        val adapter = ArrayAdapter(this,R.layout.spinner, transport)
        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        spinner1.adapter = adapter
    }

    private fun showTimePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                // 시간 선택이 완료되었을 때 실행되는 콜백 함수
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)

                // 선택된 시간을 TextView에 설정하여 출력
                textView.text = selectedTime
            },
            hourOfDay,
            minute,
            false // 24시간 형식 (true인 경우 오전/오후 형식)
        )

        timePickerDialog.show()
    }

}
