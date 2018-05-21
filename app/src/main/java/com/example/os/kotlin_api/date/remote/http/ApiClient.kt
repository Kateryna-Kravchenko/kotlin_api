package com.example.os.kotlin_api.date.remote.http

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
     fun createRetrofitInstance(): ApiService {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService::class.java)
    }

}

