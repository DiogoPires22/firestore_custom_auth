package com.diogopires.firebase_auth.data.repository

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class BaseRemote {


    protected fun <T> create(clazz: Class<T>, baseUrl: String): T {
        return retrofit(baseUrl).create(clazz)
    }

    private fun retrofit(baseUrl: String): Retrofit {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient()
                .newBuilder()
                .addInterceptor(logging)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseUrl).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))

                .build()
    }


    companion object {
        const val BASE_URL= "YOUR FUNCTION DOMAIN"
    }
}