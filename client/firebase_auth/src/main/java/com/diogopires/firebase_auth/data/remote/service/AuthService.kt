package com.diogopires.firebase_auth.data.remote.service

import com.diogopires.firebase_auth.data.entity.AuthInfo
import retrofit2.Call
import retrofit2.http.GET


interface AuthService {
    @GET("auth")
    fun auth(): Call<AuthInfo>
}