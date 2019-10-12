package com.example.kotlinrecyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_recycler.view.*

class CustomAdapter(val listOfItems:ArrayList<Data>,val dataAccesed :DataCicked ): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_item_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataValue :Data = listOfItems[position]
        holder.text_age.text=dataValue.age.toString()
        holder.text_name.text=dataValue.name
        holder.image_view.setImageResource(dataValue.image)
//        holder.row.setOnClickListener {
//            dataAccesed.setObjectValue(dataValue)
//        }
        holder.dataObject=dataValue
    }

    class ViewHolder(itemView: View,var dataObject:Data?=null) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener(View.OnClickListener {
                val intent=Intent(itemView.context,Main2Activity::class.java)
                intent.putExtra("name",dataObject?.name)
                 itemView.context.startActivity(intent)

            })
        }
        val text_name=itemView.text_name_id  as TextView
        val  text_age=itemView.text_age_id as TextView
        val image_view=itemView.image_id as ImageView
        val row=itemView.row_clicked as ConstraintLayout

    }

interface DataCicked
{
    fun setObjectValue(objectValue:Data)
}
}