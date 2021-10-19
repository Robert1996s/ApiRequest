package com.example.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.example.shortcutapp.Comic

class MyViewModel() : ViewModel() {


    private val  _response: MutableLiveData<Response<Comic>> = MutableLiveData()

    val response : LiveData<Response<Comic>> = _response

    val currentNumber: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun printText() {
        println("!!! function from viewmodel")
    }

    fun getApiData() {

        //Randomize the comic that we request
        var randomComic = (1..100).random()
        var x = randomComic.toString()

        //TODO Save a variable and put (object, comics) in a list, then display the list with an adapter
        val url = "https://xkcd.com/" + x + "/info.0.json"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                Log.d("success Request", response.toString())
                //textView.text = response.toString() Old version to set text

            },
            Response.ErrorListener {
                Log.d("error", it.localizedMessage)
            })


        // Add the request to the RequestQueue.
        //queue.add(stringRequest)
    }

}