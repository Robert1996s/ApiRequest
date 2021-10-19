package com.example.shortcutapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/info.0.json")
    fun getData(): Call<List<Comic>>
}