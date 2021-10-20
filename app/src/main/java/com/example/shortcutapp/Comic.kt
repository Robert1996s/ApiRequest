package com.example.shortcutapp

import com.beust.klaxon.Json
import com.google.gson.annotations.SerializedName

data class Comic (
    var description : String? = null,
    var detail :  String?  = null,
    var number : Int = 0,
    var image : String? = null,
    var title : String? = null
        ) {
        override fun toString() = "$description - $title"
}