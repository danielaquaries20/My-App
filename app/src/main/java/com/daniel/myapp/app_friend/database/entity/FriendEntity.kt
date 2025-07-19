package com.daniel.myapp.app_friend.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class FriendEntity(
    var name: String,
    var school: String,
    var hobby: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
