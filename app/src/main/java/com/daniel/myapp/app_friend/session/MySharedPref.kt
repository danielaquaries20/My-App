package com.daniel.myapp.app_friend.session

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class MySharedPref(val context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveName(value: String) {
        pref.edit {
            this.putString(KEY_NAME, value)
        }
    }

    fun getName(): String? {
        return pref.getString(KEY_NAME, "-")
    }

    companion object {
        const val PREF_NAME = "my_shared_pref"
        const val KEY_NAME = "name"
    }
}