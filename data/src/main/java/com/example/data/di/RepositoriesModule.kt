package com.example.data.di

import com.example.data.repository.DBRepository
import com.example.data.repository.HomeRepository
import com.example.data.repository.SearchRepository
import com.example.domain.repository.IHomeRepository
import com.example.domain.repository.IRoomDBRepository
import com.example.domain.repository.ISearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun providerHomeRepository(homeRepository: HomeRepository): IHomeRepository {
        return homeRepository
    }
    @Provides
    @Singleton
    fun providerRoomRepository(dBRepository: DBRepository): IRoomDBRepository {
        return dBRepository
    }
    @Provides
    @Singleton
    fun providerSearchRepository(searchRepository: SearchRepository): ISearchRepository {
        return searchRepository
    }
}