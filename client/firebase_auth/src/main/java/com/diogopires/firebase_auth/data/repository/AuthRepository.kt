package com.diogopires.firebase_auth.data.repository

import android.arch.lifecycle.MutableLiveData
import com.diogopires.firebase_auth.data.entity.AuthInfo
import com.diogopires.firebase_auth.data.remote.service.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface AuthRepository{
    fun getInfo(): MutableLiveData<AuthInfo?>
}

class AuthRepositoryImpl:BaseRemote(), AuthRepository{

    override fun getInfo():MutableLiveData<AuthInfo?>{

         val data = MutableLiveData<AuthInfo?>()

         create(AuthService::class.java, BASE_URL).auth().enqueue(object : Callback<AuthInfo>{
             override fun onResponse(call: Call<AuthInfo>, response: Response<AuthInfo>) {
                 if (response.isSuccessful)
                 {
                     data.setValue(response.body())
                 }else{
                     data.postValue(null)
                 }
             }

             override fun onFailure(call: Call<AuthInfo>, t: Throwable) {
                 data.postValue(null)
             }
         })
         return data
    }
}