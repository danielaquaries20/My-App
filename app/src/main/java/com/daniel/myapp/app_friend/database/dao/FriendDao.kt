package com.daniel.myapp.app_friend.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.crocodic.core.data.CoreDao
import com.daniel.myapp.app_friend.database.entity.FriendEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDao: CoreDao<FriendEntity> {
    @Query("SELECT * FROM friends ORDER BY name ASC")
    fun getAllFriends(): Flow<List<FriendEntity>>

    @Query("SELECT * FROM friends")
    fun getAll() : Flow<List<FriendEntity>>

    @Query("SELECT * FROM friends WHERE id = :id")
    fun getFriendById(id: Int): Flow<FriendEntity>
}