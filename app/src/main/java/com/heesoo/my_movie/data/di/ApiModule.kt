package com.heesoo.my_movie.data.di

import android.util.Log
import com.heesoo.core.network.HeaderInterceptor
import com.heesoo.my_movie.data.api.DiscoverApi
import com.heesoo.my_movie.data.constants.NetworkingConstants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private val json = Json { ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()
    private val TAG = this::class.java.simpleName

    @Singleton
    @Provides
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor {
                    try {
                        JSONObject(it)
                        Log.i(TAG, it)
                    } catch (e: Exception) {
                        Log.i(TAG, it)
                    }
                }.setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkingConstants.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideDiscoverApi(retrofit: Retrofit): DiscoverApi {
        return retrofit.create(DiscoverApi::class.java)
    }
}
