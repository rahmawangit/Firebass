package com.example.firebaseareef.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseareef.R
import com.example.firebaseareef.adapters.EmpAdapter
import com.example.firebaseareef.models.EmployeeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivity : AppCompatActivity() {

    private lateinit var fRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var empList : ArrayList<EmployeeModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        fRecyclerView = findViewById(R.id.recyclerView)
        fRecyclerView.layoutManager = LinearLayoutManager(this)
        fRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoad)
        empList = arrayListOf<EmployeeModel>()

        getEmployeeData()

    }

    private fun getEmployeeData(){
        fRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(EmployeeModel::class.java)
                        empList.add(empData!!)
                    }
                    val madapter = EmpAdapter(empList)
                    fRecyclerView.adapter = madapter

                    madapter.setOnItemClickListener(object : EmpAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity,EmployeeDetailsActivity::class.java)
                            intent.putExtra("empId",empList[position].fId)
                            intent.putExtra("empName",empList[position].fName)
                            intent.putExtra("empAge",empList[position].fAge)
                            intent.putExtra("empSalary",empList[position].fSalary)
                            startActivity(intent)
                        }
                    })

                    fRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}