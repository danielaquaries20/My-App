package com.daniel.myapp.app_friend.database.repo.friend

import com.daniel.myapp.app_friend.database.entity.FriendEntity
import kotlinx.coroutines.flow.Flow


interface FriendRepository {

    suspend fun getAllFriends(): Flow<List<FriendEntity>>

    suspend fun getAll(): Flow<List<FriendEntity>>

    suspend fun getFriendById(id: Int): Flow<FriendEntity>
}