package com.piranha.apimock

import com.google.gson.annotations.SerializedName

data class ResponseList(

@field:SerializedName("data") val data:List<ListData>
)

class ListData (
@SerializedName("title") val title : String,
@SerializedName("complete") val complete : Boolean,
@SerializedName("content") val content : String,
@SerializedName("id") val id : Int,

    )
