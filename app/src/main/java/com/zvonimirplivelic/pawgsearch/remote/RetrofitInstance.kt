package com.zvonimirplivelic.pawgsearch.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zvonimirplivelic.pawgsearch.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: PAWGSearchService by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(PAWGSearchService::class.java)
    }
}