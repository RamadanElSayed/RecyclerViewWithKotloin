package com.example.kotlinrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration

class MainActivity : AppCompatActivity(), CustomAdapter.DataCicked {
    override fun setObjectValue(objectValue: Data) {
        Toast.makeText(this,""+objectValue.name,Toast.LENGTH_LONG).show()
       // startActivity(Intent(this,Main2Activity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listOfData=ArrayList<Data>()
        listOfData.add(Data("رمضان",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("احمد",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("رمضان",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("رمضان",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("رمضان",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("رمضان",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("محمد",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("رمضان",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("رمضان",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("رمضان",12,R.drawable.ic_launcher_foreground))
        listOfData.add(Data("محمود",12,R.drawable.ic_launcher_foreground))

        recycler_view_id.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
         val adapter=CustomAdapter(listOfData,this)
        recycler_view_id.adapter=adapter
    }
}
