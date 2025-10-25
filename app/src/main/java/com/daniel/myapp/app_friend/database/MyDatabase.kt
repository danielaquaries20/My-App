package com.daniel.myapp.app_friend.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.daniel.myapp.app_friend.database.dao.FriendDao
import com.daniel.myapp.app_friend.database.entity.FriendEntity

@Database(
    entities = [FriendEntity::class],
    version = 9,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(3,4),
        AutoMigration(4, 5, MyDatabase.RenameFrndBioToDescription::class),
        AutoMigration(5, 6, MyDatabase.DeleteFrndDescription::class),
        AutoMigration(6,7),
        AutoMigration(7, 8, MyDatabase.RenameFrndDescToBio::class),
        AutoMigration(8, 9, MyDatabase.DeleteFrndBio::class)
    ]
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendDao

    @RenameColumn("friends", "desc", "description")
    class RenameFrndBioToDescription : AutoMigrationSpec

    @DeleteColumn("friends", "description")
    class DeleteFrndDescription : AutoMigrationSpec

    @RenameColumn("friends", "desc", "bio")
    class RenameFrndDescToBio : AutoMigrationSpec

    @DeleteColumn("friends", "bio")
    class DeleteFrndBio : AutoMigrationSpec

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