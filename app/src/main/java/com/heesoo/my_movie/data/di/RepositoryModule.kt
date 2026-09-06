package com.heesoo.my_movie.data.di

import com.heesoo.my_movie.data.repository.DiscoverRepositoryImpl
import com.heesoo.my_movie.data.repository.FavoriteRepositoryImpl
import com.heesoo.my_movie.data.repository.MovieRepositoryImpl
import com.heesoo.my_movie.data.repository.SearchRepositoryImpl
import com.heesoo.my_movie.domain.repository.DiscoverRepository
import com.heesoo.my_movie.domain.repository.FavoriteRepository
import com.heesoo.my_movie.domain.repository.MovieRepository
import com.heesoo.my_movie.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDiscoverRepository(impl: DiscoverRepositoryImpl): DiscoverRepository

    @Binds
    @Singleton
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository
}
