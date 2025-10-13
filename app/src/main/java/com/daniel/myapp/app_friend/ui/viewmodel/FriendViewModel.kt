package com.daniel.myapp.app_friend.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniel.myapp.app_friend.base.BaseViewModel
import com.daniel.myapp.app_friend.database.dao.FriendDao
import com.daniel.myapp.app_friend.database.entity.FriendEntity
import com.daniel.myapp.app_friend.database.repo.friend.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendDao: FriendDao,
    private val friendRepository: FriendRepository,
) : BaseViewModel() {

    private val _friends = MutableSharedFlow<List<FriendEntity>>()
    val friends = _friends.asSharedFlow()

//    fun getFriend() = friendDao.getAllFriends()

    fun getFriend() = viewModelScope.launch {
        friendRepository.getAllFriends().collect {
            _friends.emit(it)
        }
    }

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