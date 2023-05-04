package com.example.retrofittut

import retrofit2.Call
import retrofit2.http.GET

interface API_interface  {

    // baseUrl is  = https://jsonplaceholder.typicode.com/ but @GET tells us to go to
    // https://jsonplaceholder.typicode.com/posts which is the required page
    // Using GET here because we just want to get something not send it
    @GET("posts/")
    fun getJson() : Call<List<ResponseModel>>;
    //List because we are bringing in list of ResponseModel type objects

}