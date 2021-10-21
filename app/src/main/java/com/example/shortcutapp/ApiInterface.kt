package com.example.shortcutapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/info.0.json")
    fun getData(): Call<List<Comic>>


    companion object {
        var BASE_URL = "https://xkcd.com/"

        fun create(): ApiInterface {
            val retrofit =  Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }



}