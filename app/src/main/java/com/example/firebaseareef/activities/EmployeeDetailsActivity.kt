package com.example.firebaseareef.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firebaseareef.R
import com.example.firebaseareef.models.EmployeeModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.values

class EmployeeDetailsActivity : AppCompatActivity() {
    private lateinit var tvId : TextView
    private lateinit var tvName : TextView
    private lateinit var tvAge : TextView
    private lateinit var tvSalary : TextView

    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        Log.d("awalan","BERANGKAT")
        initView()
        setValuesToViews()
        Log.d("awalan","cek init")

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("empId").toString(),
                intent.getStringExtra("empName").toString()

            )
            Log.d("awalan","after logic ")
        }
    }

    private fun openUpdateDialog(empId : String, empName : String){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView =   inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val editName = mDialogView.findViewById<TextView>(R.id.valName)
        val editAge = mDialogView.findViewById<TextView>(R.id.valAge)
        val editSalary = mDialogView.findViewById<TextView>(R.id.valSalary)
        val btnUpdateSave  =  mDialogView.findViewById<Button>(R.id.btnSetUpdate)

        editName.text = intent.getStringExtra("empName").toString()
        editAge.text = intent.getStringExtra("empAge").toString()
        editSalary.text = intent.getStringExtra("empSalary").toString()

        mDialog.setTitle("Updating $editName Record")
        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateSave.setOnClickListener {
            updateEmpData(
                empId,
                editName.text.toString(),
                editAge.text.toString(),
                editSalary.text.toString()
            )
        }
        Toast.makeText(applicationContext,"Employee Data Updated", Toast.LENGTH_LONG).show()
        editName.text = editName.text.toString()
        editAge.text = editAge.text.toString()
        editSalary.text = editSalary.text.toString()
        alertDialog.dismiss()
    }

    private fun updateEmpData(
        id:String,
        name:String,
        age:String,
        salary:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val empInfo = EmployeeModel(id,name,age,salary)
        dbRef.setValue(empInfo)
    }

    private fun initView(){
        tvId = findViewById(R.id.vdId)
        tvName = findViewById(R.id.vdName)
        tvAge = findViewById(R.id.vdAge)
        tvSalary = findViewById(R.id.vdSalary)
        btnUpdate = findViewById(R.id.buttUpdate)
        btnDelete = findViewById(R.id.buttDelete)
    }

    private fun setValuesToViews(){

        tvId.text = intent.getStringExtra("empId")
        tvName.text = intent.getStringExtra("empName")
        tvAge.text = intent.getStringExtra("empAge")
        tvSalary.text = intent.getStringExtra("empSalary")

    }
}