package com.daniel.myapp.app_friend.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.daniel.myapp.app_friend.database.dao.FriendDao
import com.daniel.myapp.app_friend.database.entity.FriendEntity

class FriendViewModel(private val friendDao: FriendDao) : ViewModel() {

    fun getFriend() = friendDao.getAllFriends()

    fun getFriendById(id: Int) = friendDao.getFriendById(id)

    suspend fun insertFriend(data: FriendEntity) {
        friendDao.insert(data)
    }

    suspend fun updateFriend(data: FriendEntity) {
        friendDao.update(data)
    }

    suspend fun deleteFriend(data: FriendEntity) {
        friendDao.delete(data)
    }

}