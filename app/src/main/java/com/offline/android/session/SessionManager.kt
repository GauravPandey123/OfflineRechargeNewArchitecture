package com.offline.android.session

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager
@Inject
constructor(
    val application: Application
) {
    companion object {
        const val APP_PREFERENCES: String = "com.offline.android.APP_PREFERENCES"

        private const val USER_PREF = "user_pref"
        private const val USER_TOKEN = "user_token"
        private const val USER_DATA = "user_data"
        private const val FCM_TOKEN = "fcm_token"
        private const val SIGNIN_DATA = "signin_data"
        private const val FCM_TOKEN_DATA = "FCM_TOKEN_DATA"
        private const val LATTITUDE = "lattitude"
        private const val LONGITUDE = "longitude"
        private const val USERRESPONSE="USERRESPONSE"
    }


    fun clearlocalData(context: Context) {
        context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE).edit().clear().apply()
    }

    var fcmToken: String?
        get() = pref.getString(FCM_TOKEN, null)
        set(value) = pref.edit().putString(FCM_TOKEN, value).apply()

    fun clearUserData(context: Context?) {
        context?.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)?.edit()?.clear()?.apply()
    }


    private val pref: SharedPreferences = application.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE)




    var lattitude: String?
        get() = pref.getString(LATTITUDE, null)
        set(value) = pref.edit().putString(LATTITUDE, value).apply()

    var longitude: String?
        get() = pref.getString(LONGITUDE, null)
        set(value) = pref.edit().putString(LONGITUDE, value).apply()


    fun isConnectedToTheInternet(): Boolean{
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try{
            return cm.activeNetworkInfo.isConnected
        }catch (e: Exception){
            Log.e(TAG, "isConnectedToTheInternet: ${e.message}")
        }
        return false
    }

}


