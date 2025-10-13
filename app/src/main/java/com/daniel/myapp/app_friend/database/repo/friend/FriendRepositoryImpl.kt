package com.daniel.myapp.app_friend.database.repo.friend

import com.daniel.myapp.app_friend.database.dao.FriendDao
import com.daniel.myapp.app_friend.database.entity.FriendEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(private val friendDao: FriendDao) :  FriendRepository {
    override suspend fun getAllFriends(): Flow<List<FriendEntity>> {
        return friendDao.getAllFriends()
    }

    override suspend fun getAll(): Flow<List<FriendEntity>> {
        return friendDao.getAll()
    }

    override suspend fun getFriendById(id: Int): Flow<FriendEntity> {
        return friendDao.getFriendById(id)
    }
}