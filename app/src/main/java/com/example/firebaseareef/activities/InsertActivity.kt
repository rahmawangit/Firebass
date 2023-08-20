package com.example.firebaseareef.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebaseareef.R
import com.example.firebaseareef.models.EmployeeModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertActivity : AppCompatActivity() {
    private lateinit var inpName : EditText
    private lateinit var inpAge : EditText
    private lateinit var inpSalary : EditText
    private lateinit var btnSave : Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_activity)

        inpName = findViewById(R.id.inpName)
        inpAge = findViewById(R.id.inpAge)
        inpSalary = findViewById(R.id.inpSalary)
        btnSave = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        btnSave.setOnClickListener(){
            saveData()
        }

    }

    private fun saveData(){
        val fName = inpName.text.toString()
        val fAge = inpAge.text.toString()
        val fSalary = inpSalary.text.toString()

        if(fName.isEmpty()){
            inpName.error = "Please Enter Name"
        }
        if(fAge.isEmpty()){
            inpName.error = "Please Enter Age"
        }
        if(fSalary.isEmpty()){
            inpName.error = "Please Enter Salary"
        }

        val fId = dbRef.push().key!!

        val employee = EmployeeModel(fId,fName,fAge,fSalary)

        dbRef.child(fId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Berhasil Masuk",Toast.LENGTH_LONG).show()
            }.addOnFailureListener{err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }

    }
}