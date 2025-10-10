package com.daniel.myapp.middle.aa_shared_pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class MiddleSession(context: Context) {

    companion object {
        const val PREF_NAME = "middle_app_prefs"
        const val KEY_COUNT = "last_count"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getCount(): Int {
        return sharedPreferences.getInt(KEY_COUNT, 0)
    }

    fun saveCount(newCount: Int) {
        sharedPreferences.edit { putInt(KEY_COUNT, newCount) }
    }
}