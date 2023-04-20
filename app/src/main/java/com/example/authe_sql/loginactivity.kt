package com.example.authe_sql

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class loginactivity : AppCompatActivity() {

    lateinit var my_email:EditText
    lateinit var my_pass:EditText
    lateinit var my_reg:Button
    lateinit var my_log:Button
    lateinit var db:SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        my_email = findViewById(R.id.edtemail)
        my_pass = findViewById(R.id.edtpass)
        my_reg = findViewById(R.id.btnR)
        my_log = findViewById(R.id.btnL)

        db =openOrCreateDatabase("EmobilisDB", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS users( arafa VARCHAR, siri VARCHAR)")


        my_log.setOnClickListener {

            var email = my_email.text.toString().trim()
            var password = my_pass.text.toString().trim()

            //validate
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            }
            else{

                val cursor = db.rawQuery("SELECT * FROM users WHERE arafa=? AND siri=?", arrayOf(email, password))

                if (cursor != null && cursor.moveToFirst()) {
                    // user is authenticated, start a new activity
                    val intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid email or password, please try again", Toast.LENGTH_SHORT).show()
                }

            }

        }
        my_reg.setOnClickListener {

            Toast.makeText(this, "User Successfully Registered", Toast.LENGTH_SHORT).show()

            var gotomain = Intent(this, MainActivity::class.java)
            startActivity(gotomain)

        }
    }
}