package com.example.k_employeemanagement_application.utils

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.SharingStarted

class SessionManager(context: Context) {
    private  val prefs: SharedPreferences=
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    companion object{
        private const val PREF_NAME = "user_session"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_ROLE = "role"
    }

    fun saveSassion(isLoggedIn: Boolean, username: String, email: String, role: String){
        prefs.edit().apply{
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            putString(KEY_USERNAME, username)
            putString(KEY_EMAIL, email)
            putString(KEY_ROLE, role)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun getUsername(): String? = prefs.getString(KEY_USERNAME, null)
    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)
    fun getRole(): String? = prefs.getString(KEY_ROLE, null)




}