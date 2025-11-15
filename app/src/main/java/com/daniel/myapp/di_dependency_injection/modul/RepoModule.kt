package com.daniel.myapp.di_dependency_injection.modul

import com.daniel.myapp.app_friend.database.repo.friend.FriendRepository
import com.daniel.myapp.app_friend.database.repo.friend.FriendRepositoryImpl
import com.daniel.myapp.app_tour.repo.DataProductRepository
import com.daniel.myapp.app_tour.repo.DataProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun bindFriendRepository(implFriendRepository: FriendRepositoryImpl): FriendRepository

    @Singleton
    @Binds
    abstract fun bindDataProductRepository(implDataProductRepository: DataProductRepositoryImpl): DataProductRepository
}