package com.example.firebaseareef.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firebaseareef.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var btnInsert : Button
    private lateinit var btnFetchData : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
        btnInsert = findViewById(R.id.btnInsert)
        btnFetchData = findViewById(R.id.btnFetchData)

        btnInsert.setOnClickListener(){
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }

        btnFetchData.setOnClickListener(){
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }
    }
}