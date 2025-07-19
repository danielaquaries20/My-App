package com.daniel.myapp.app_friend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.daniel.myapp.app_friend.database.dao.FriendDao
import com.daniel.myapp.app_friend.database.entity.FriendEntity

@Database(
    entities = [FriendEntity::class],
    version = 2,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendDao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            val instance = Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "my_database"
            )
                .fallbackToDestructiveMigration(false)
                .build()

            INSTANCE = instance
            return instance
        }
    }
}