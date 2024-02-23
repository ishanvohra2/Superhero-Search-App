package com.ishanvohra2.superheroqueryapp.network

import com.ishanvohra2.superheroqueryapp.data.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("search/{name}")
    suspend fun searchSuperhero(@Path("name")name: String): Response<SearchResponse>

}