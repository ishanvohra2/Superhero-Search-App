package com.ishanvohra2.superheroqueryapp.repository

import com.ishanvohra2.superheroqueryapp.network.NewsRetrofitClient
import org.koin.java.KoinJavaComponent

class NewsRepository {

    private val retrofitClient by KoinJavaComponent.inject<NewsRetrofitClient>(NewsRetrofitClient::class.java)

    suspend fun getNews(page: Int) = retrofitClient.instance.getNews(page = page)

}