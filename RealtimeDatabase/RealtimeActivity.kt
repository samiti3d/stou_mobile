package com.stou.firebase.RealtimeDatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.stou.firebase.R
import kotlinx.android.synthetic.main.activity_realtime.*

class RealtimeActivity : AppCompatActivity() {

    private lateinit var database:FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtime)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        btn_send.setOnClickListener{
            sendData()
        }

        btn_getdata.setOnClickListener {
            startActivity(Intent(applicationContext, Getdata::class.java))
        }

    }

    private fun sendData(){

        var name = et_name.text.toString().trim()
        var email = et_email.text.toString().trim()

        if(name.isNotEmpty() && email.isNotEmpty())
        {
            var model = DatabaseModel(name, email)
            var id = reference.push().key

            //Send the data to Firebase
            reference.child(id!!).setValue(model)
            et_name.setText("")
            et_email.setText("")

        }else
        {
            Toast.makeText(applicationContext, "All Field Required", Toast.LENGTH_LONG).show()
        }
    }
}