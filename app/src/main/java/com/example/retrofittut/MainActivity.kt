package com.example.retrofittut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var tv : TextView
    private var c =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById<TextView>(R.id.tv)
        val bt = findViewById<Button>(R.id.button)

        bt.setOnClickListener {
           makeApiCall(c)
            c++ //Our Counter So to Keep Skipping To Next data

        }

    }

    public fun makeApiCall(c : Int) {
        val baseurl : String = "https://jsonplaceholder.typicode.com/"
        //base url is given in the API

        val retrofitbuilder = Retrofit.Builder() //Retrofit Builder
            .baseUrl(baseurl) //Supply the destination
            .addConverterFactory(GsonConverterFactory.create()) //Gson Converter To convert Object to Json
            .build()
            .create(API_interface::class.java) //Our interface class

        val retrofitdata = retrofitbuilder.getJson() //Our Function from @GET in API_Interface


        retrofitdata.enqueue(object : Callback<List<ResponseModel>> {
            override fun onResponse(call: Call<List<ResponseModel>>, response: Response<List<ResponseModel>>) {
                val responseBody = response.body()
                val responseObj = responseBody!!.get(c) //to get the cth object from list and it updates with every button press
                //Response-->responseBody-->responseObject-->responseObj.title
                if (responseBody != null) {
                    tv.setText(responseObj.title)
                }

            }

            override fun onFailure(call: Call<List<ResponseModel>>, t: Throwable) {
                Log.d("TAG", ""+t.message!! )
                Toast.makeText(this@MainActivity, "Something Went Wrong : "+ t.message, Toast.LENGTH_LONG).show()
            }


        }
        )
    }


}