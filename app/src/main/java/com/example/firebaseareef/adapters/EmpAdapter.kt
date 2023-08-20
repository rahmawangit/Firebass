package com.example.firebaseareef.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseareef.R
import com.example.firebaseareef.models.EmployeeModel

class EmpAdapter(private val empList:ArrayList<EmployeeModel>) : RecyclerView.Adapter<EmpAdapter.ViewHolder>(){
    var x =1
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.,parent,false)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false)
        return ViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(
        holder: EmpAdapter.ViewHolder,
        position: Int
    ) {
        val currentList  = empList[position]
        holder.tvName.text = currentList.fName

    }

    override fun getItemCount(): Int {
        return empList.size
    }

    class ViewHolder(itemView : View,clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val tvName : TextView = itemView.findViewById(R.id.tvName)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}