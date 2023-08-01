package com.example.lets_trip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class HomeActivity : AppCompatActivity() {

    lateinit var homeButton: ImageButton
    lateinit var newtripButton: ImageButton
    lateinit var mytripButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        newtripButton = findViewById(R.id.image1)
        homeButton = findViewById(R.id.image2)
        mytripButton = findViewById(R.id.image3)


        newtripButton.setOnClickListener {
                val intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
        }

        homeButton.setOnClickListener {
            val intent1 = Intent(this, HomeActivity::class.java)
            startActivity(intent1)
        }

        mytripButton.setOnClickListener {
            val intent1 = Intent(this, MytripActivity::class.java)
            startActivity(intent1)
        }

    }
}