package com.example.getandpostrequests

import com.google.gson.annotations.SerializedName

class Name {

    @SerializedName("pk")
    val id: Int? = null

    @SerializedName("name")
    var name: String? = null
}