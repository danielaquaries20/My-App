package com.daniel.myapp.di_dependency_injection.modul

import android.content.Context
import com.daniel.myapp.app_friend.database.MyDatabase
import com.daniel.myapp.middle.aa_shared_pref.MiddleSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMiddleSession(@ApplicationContext context: Context) = MiddleSession(context)

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): MyDatabase =
        MyDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideFriendDao(database: MyDatabase) = database.friendDao()
}