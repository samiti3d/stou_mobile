package com.stou.firebase.RealtimeDatabase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.stou.firebase.R
import kotlinx.android.synthetic.main.activity_getdata.*

class Getdata : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getdata)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        getData()

    }

    private fun getData(){
        reference.addValueEventListener(object :ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                var list = ArrayList<DatabaseModel>()
                for(data in snapshot.children){
                    var model = data.getValue(DatabaseModel::class.java)
                    list.add(model as DatabaseModel)
                }

                if(list.size > 0 )
                {
                    val adapter = DataAdapter(list)
                    recyclerview.adapter = adapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancle", error.toString() )
            }

        })


    }
}