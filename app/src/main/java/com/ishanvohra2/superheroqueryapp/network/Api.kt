package com.ishanvohra2.superheroqueryapp.network

import com.ishanvohra2.superheroqueryapp.data.NewsResponse
import com.ishanvohra2.superheroqueryapp.data.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/{name}")
    suspend fun searchSuperhero(@Path("name")name: String): Response<SearchResponse>

    @GET("everything")
    suspend fun getNews(
        @Query("q") searchQuery: String = "real estate",
        @Query("apiKey") apiKey: String = "613410bc8e0e4b66984a2d99ab06a1c5",
        @Query("page")page: Int
    ): Response<NewsResponse>

}