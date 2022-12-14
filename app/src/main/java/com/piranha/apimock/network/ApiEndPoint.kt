package com.piranha.apimock.network

import com.google.gson.JsonObject
import com.piranha.apimock.ResponseList
import com.piranha.apimock.model.ResponseAdd
import com.piranha.apimock.model.ResponseDelete
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiEndPoint {
    @GET("todo")
    fun getList(): Call<ResponseList>

    @POST("todo")
    fun Addtodo(
        @Body jobj: JsonObject
    ):Call<ResponseAdd>

    @POST("todo")
    fun AddTODOFORMDATA(
        @Field("title") title: String,
        @Field("complete") complete: Boolean,
        @Field("content") content: String,
    ): Call<ResponseAdd>

    @DELETE("todo/{id}")
    fun DeleteTODO(
        @Path("id") id: String
    ):Call<ResponseDelete>

}