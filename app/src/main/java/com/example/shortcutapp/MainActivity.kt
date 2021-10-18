package com.example.shortcutapp

import android.net.Network
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.example.NetWork.NetWorkHandler
import com.example.ViewModels.MyViewModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


class MainActivity : AppCompatActivity() {

    private var currList = mutableListOf<Comic>()
    private var apiComics = mutableListOf<Int>()
    lateinit var answer : String

    lateinit var viewModel: MyViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //current empty list of comics
        currList.add(0, Comic("Desc", "Detail", 0, "", "Comic 1"))
        currList.add(1, Comic("Desc", "Det", 1, "", "Comic 2"))


        val mapper = jacksonObjectMapper()


        val getApiBtn = findViewById<Button>(R.id.getApi_button)
        val textView = findViewById<TextView>(R.id.textView)
        val recycleView = findViewById<RecyclerView>(R.id.recycle_view)
        val adapter = Adapter(this, currList)

        if (NetWorkHandler.isOnline(this)) {
            getApiBtn.isEnabled = true
            println("!!! Online")
        } else {
            getApiBtn.isEnabled = false
            println("!!! Offline")
        }

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        //viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        var randomComic = (1..100).random()

        getApiBtn.setOnClickListener {

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            var x = randomComic.toString()

            // the 0 beetween com and info is the comic number https://xkcd.com/0/info.0.json

            //TODO Save a variable and put (object, comics) in a list, then display the list with an adapter
            val url = "https://xkcd.com/" + x + "/info.0.json"

            /*val result  = Klaxon()
                    .parse<Comic>("""
                        {
                        "title": "Comic title",
                        }
                    """.trimIndent())

            assert(result?.title  == "Comic Title") */

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    Log.d("success Request", response.toString())
                    textView.text = response.toString()
                    answer = response

                },
                Response.ErrorListener {
                    Log.d("error", it.localizedMessage)
                })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)


        }

        //val dataFromApi = mapper.readValue<Comic>(answer)


    }

    /*private fun getText() {
        getApi_button.setOnClickListener {
            viewModel.printText()
        }
    } */
}