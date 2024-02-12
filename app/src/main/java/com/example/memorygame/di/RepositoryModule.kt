package com.example.memorygame.di

import com.example.memorygame.domain.AppRepository
import com.example.memorygame.domain.AppRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAppRepository(imp : AppRepositoryImp): AppRepository
}