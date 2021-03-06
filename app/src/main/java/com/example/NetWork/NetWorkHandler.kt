package com.example.NetWork

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetWorkHandler {

    companion object {
        @RequiresApi(Build.VERSION_CODES.M)

        fun isOnline(context: Context) : Boolean {
            val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                    connectionManager.getNetworkCapabilities(connectionManager.activeNetwork)
            if (capabilities != null) {
                when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        println("!!! Internet: Celular")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        println("!!! Internet: WIFI")
                        return true
                    }
                }
            }
            return false
        }
    }
}