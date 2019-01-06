package com.diogopires.example_firestore_custom_token

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.diogopires.firebase_auth.AuthManager
import com.diogopires.firebase_auth.FirebaseAuthManager
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity  : AppCompatActivity() {

    private val authManager: AuthManager = FirebaseAuthManager()


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            authManager.login(this,{
                Toast.makeText(this,"firebase login successfully",Toast.LENGTH_SHORT).show()

                FirebaseFirestore.getInstance()
                        .collection("/teste").get()
                        .addOnSuccessListener {
                            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this,"Error removing document.",Toast.LENGTH_SHORT).show()
                        }
            },{
                Toast.makeText(this,"firebase login failure",Toast.LENGTH_SHORT).show()
            })

        }
    }

