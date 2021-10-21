package com.example.getandpostrequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var tv: TextView
    lateinit var input: EditText
    lateinit var save: Button
    lateinit var get: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = APIClient.getClient()?.create(APIInterface::class.java)

        tv = findViewById(R.id.tv)
        input = findViewById(R.id.et)
        save = findViewById(R.id.button)
        get = findViewById(R.id.button2)

        save.setOnClickListener {
            if(input.text.isNotEmpty()){
                val name = Name()
                name.name = input.text.toString()

                apiInterface?.addName(name)?.enqueue(object : Callback<Name> {
                    override fun onResponse(call: Call<Name>, response: Response<Name>) {
                        Toast.makeText(this@MainActivity, "Saved", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<Name>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Try Again", Toast.LENGTH_LONG).show()
                    }

                })
            }else{
                Toast.makeText(this, "Enter a Name", Toast.LENGTH_LONG).show()
            }
        }

        get.setOnClickListener {
            val call: Call<List<Name>> = apiInterface!!.getNames()
            call.enqueue(object : Callback<List<Name>>{
                override fun onResponse(call: Call<List<Name>>, response: Response<List<Name>>) {
                    for(name in response.body()!!){
                        tv.text = "${tv.text} - ${name.name}"
                    }
                }

                override fun onFailure(call: Call<List<Name>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Try Again", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}