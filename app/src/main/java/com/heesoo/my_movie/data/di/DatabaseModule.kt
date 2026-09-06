package com.heesoo.my_movie.data.di

import android.content.Context
import androidx.room.Room
import com.heesoo.my_movie.data.local.AppDatabase
import com.heesoo.my_movie.data.local.dao.FavoriteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()

    @Singleton
    @Provides
    fun provideFavoriteMovieDao(appDatabase: AppDatabase): FavoriteMovieDao =
        appDatabase.favoriteMovieDao()
}
