package com.daniel.myapp.app_friend.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.daniel.myapp.app_friend.database.MyDatabase

class FriendVMFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return FriendViewModel(MyDatabase.getInstance(context).friendDao()) as T
    }
}