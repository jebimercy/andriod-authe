package com.example.authe_sql

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var myname1:EditText
    lateinit var myname2:EditText
    lateinit var myemail:EditText
    lateinit var mypass:EditText
    lateinit var myaccount:Button
    lateinit var db:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myname1 =findViewById(R.id.txt1)
        myname2 =findViewById(R.id.txt2)
        myemail =findViewById(R.id.txtemail)
        mypass =findViewById(R.id.txtpass)
        myaccount =findViewById(R.id.btncreate)

        db =openOrCreateDatabase("EmobilisDB", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS users(jina1 VARCHAR, jina2 VARCHAR, arafa VARCHAR, kitambulisho VARCHAR)")

        myaccount.setOnClickListener {

            var firstname = myname1.text.toString()
            var secondname = myname2.text.toString()
            var email = myemail.text.toString()
            var password = mypass.text.toString()
            //Check if the user is trying to submit empty records
            if (firstname.isEmpty() || secondname.isEmpty() || email.isEmpty() || password.isEmpty()){
                //Use the display_message() to Display a message telling the user to fill all the inputs

                //toast a message to say cannot submit empty field


            }else{
                //Proceed to save your data into the db
                db.execSQL("INSERT INTO users VALUES('"+firstname+"','"+secondname+"','"+email+"','"+password+"')")


                //Toast a success message
                Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()

            }

        }

    }
}