package com.diogopires.firebase_auth

import android.content.Context

interface AuthManager {
    fun login(context: Context, onSuccess: () -> Unit, onFailure: () -> Unit)
}