package com.daniel.myapp.di_dependency_injection.modul

import android.content.Context
import com.daniel.myapp.middle.aa_shared_pref.MiddleSession
import com.daniel.myapp.middle.counter.CounterViewModel
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

}