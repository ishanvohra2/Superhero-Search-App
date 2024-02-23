package com.ishanvohra2.superheroqueryapp.repository

import com.ishanvohra2.superheroqueryapp.network.RetrofitClient
import org.koin.java.KoinJavaComponent.inject

class SearchRepository {

    private val retrofitClient by inject<RetrofitClient>(RetrofitClient::class.java)

    suspend fun searchSuperhero(name: String) = retrofitClient.instance.searchSuperhero(name)

}