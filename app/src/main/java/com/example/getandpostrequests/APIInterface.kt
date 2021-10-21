package com.example.getandpostrequests

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {

    @GET("/custom-people/")
    fun getNames(): Call<List<Name>>

    @POST("/custom-people/")
    fun addName(@Body name: Name): Call<Name>
}