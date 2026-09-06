package com.heesoo.my_movie.data.di

import com.heesoo.my_movie.data.remote.DiscoverRemoteDataSource
import com.heesoo.my_movie.data.remote.DiscoverRemoteDataSourceImpl
import com.heesoo.my_movie.data.remote.MovieRemoteDataSource
import com.heesoo.my_movie.data.remote.MovieRemoteDataSourceImpl
import com.heesoo.my_movie.data.remote.SearchRemoteDataSource
import com.heesoo.my_movie.data.remote.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindDiscoverRemoteDataSource(impl: DiscoverRemoteDataSourceImpl): DiscoverRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindSearchRemoteDataSource(impl: SearchRemoteDataSourceImpl): SearchRemoteDataSource
}
