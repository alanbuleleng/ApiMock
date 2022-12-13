package com.piranha.apimock.network

import com.piranha.apimock.ResponseList
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {
    @GET("todo")
    fun getList(): Call<ResponseList>

}