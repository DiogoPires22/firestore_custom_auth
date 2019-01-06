package com.diogopires.firebase_auth

import android.content.Context
import com.diogopires.firebase_auth.data.entity.AuthInfo
import com.diogopires.firebase_auth.data.repository.AuthRepository
import com.diogopires.firebase_auth.data.repository.AuthRepositoryImpl
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

typealias CallbackSuccess = () -> Unit
typealias CallbackError = () -> Unit


class FirebaseAuthManager(val authRepository: AuthRepository = AuthRepositoryImpl()): AuthManager {

    override fun login(context: Context, onSuccess: CallbackSuccess, onFailure: CallbackError) {

        authRepository.getInfo().observeForever { authInfo ->

            if(authInfo!= null){
                try {
                    initializeFirebase(context,authInfo)
                    firebaseAuth(authInfo.token,{
                        onSuccess()
                    },{
                        onFailure()
                    })
                }catch (ex:Exception){
                    onFailure()
                }
            }
        }
    }

    private fun initializeFirebase(context: Context,authInfo: AuthInfo){
        val options = FirebaseOptions.Builder()
                .setApplicationId(authInfo.applicationId)
                .setProjectId(authInfo.applicationId)// Required for Analytics.
                .setApiKey(authInfo.apiKey) // Required for Auth.
                .setDatabaseUrl(authInfo.dataBaseUrl)
                  // Required for RTDB.
                .build()

        FirebaseApp.initializeApp(context, options)
    }

    private fun firebaseAuth(token:String, onSuccess: CallbackSuccess, onFailure: CallbackError){

        FirebaseAuth.getInstance().signInWithCustomToken(token)
                .addOnFailureListener {
                    onFailure()
                }
                .addOnSuccessListener {
                    onSuccess()
                }
    }
}