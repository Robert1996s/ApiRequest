package com.example.ViewModels

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.example.shortcutapp.ApiInterface
import com.example.shortcutapp.Comic
import com.example.shortcutapp.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyViewModel() : ViewModel() {



    private val BASE_URL = "https://xkcd.com/"

    private val  _response: MutableLiveData<List<Comic>> = MutableLiveData()
    val response : LiveData<List<Comic>> = _response

    var localApiList = ArrayList<Comic>()


    fun getApiResponse(): LiveData<List<Comic>>{
        return _response
    }


    fun printText() {
        println("!!! function from viewmodel")
    }

    fun getApi() {
        val apiInterface  =  ApiInterface.create().getData()

        apiInterface.enqueue( object : Callback<List<Comic>>{

            override fun onResponse(call: Call<List<Comic>>, response: retrofit2.Response<List<Comic>>) {
                if (response.body() != null){
                    //set the data to list
                    //the example set the recycleview to response.body()
                }
            }

            override fun onFailure(call: Call<List<Comic>>, error: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: " + error.message)
            }

        })


    }


    fun getApiData() {

            println("!!! inside getApiData")
            val retrofitBuilder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(ApiInterface::class.java)

            val retrofitData =  retrofitBuilder.getData()

            retrofitData.enqueue(object : Callback<List<Comic>?> {
                override fun onResponse(
                        call: Call<List<Comic>?>,
                        response: retrofit2.Response<List<Comic>?>
                ) {
                    //Data may come as null, make non null assumtion
                    val responseBody = response.body()!!

                    val myString = StringBuilder()

                    _response.value =  response.body()

                    println("!!! " + response)

                    var maxSize = 10

                    for (myData in responseBody) {
                        if (localApiList.size < maxSize) {
                            localApiList.add(myData)
                        }

                        println("!!! inside loop")

                    }

                    //textView.text  = myString ///  change value and observe in activity
                }

                override fun onFailure(call: Call<List<Comic>?>, error: Throwable) {
                    println("!!! inside getApiData")
                    Log.d(ContentValues.TAG, "onFailure: " + error.message)
                }
            })
        }
    }

