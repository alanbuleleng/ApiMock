package com.piranha.apimock.model

import com.google.gson.annotations.SerializedName

data class ResponseDelete(
    @SerializedName("title") val title:String,
    @SerializedName("complete") val complete:Boolean,
    @SerializedName("content") val content:String,
    @SerializedName("id") val id:Int
)