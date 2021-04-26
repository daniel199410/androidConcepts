package co.com.flypass.flypassNative.core

import android.content.Context
import android.content.SharedPreferences
import co.com.flypass.flypassNative.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(AppConstants.REFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(AppConstants.ACCESS_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(AppConstants.ACCESS_TOKEN, null)
    }
}