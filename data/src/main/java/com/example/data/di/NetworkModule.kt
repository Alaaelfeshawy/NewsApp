package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.source.remote.HomeApi
import com.example.domain.util.AppConstants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }

    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi)
    }
    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    @Named(AppConstants.API_VERSION_1)
    fun providesRetrofit(
        moshiConverterFactory: Converter.Factory?,
        okHttpClient: OkHttpClient?,
        @Named(AppConstants.BASE_URL) baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient!!)
            .addConverterFactory(moshiConverterFactory!!)
            .build()
    }

    @Singleton
    @Provides
    @Named(AppConstants.API_VERSION_2)
    fun providesRetrofit2(
        moshiConverterFactory: Converter.Factory?,
        okHttpClient: OkHttpClient?,
        @Named(AppConstants.BASE_URL_2) baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient!!)
            .addConverterFactory(moshiConverterFactory!!)
            .build()
    }

    @Singleton
    @Provides
    @Named(AppConstants.HOME_API_VERSION_1)
    fun provideHomeApi( @Named(AppConstants.API_VERSION_1) retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    @Named(AppConstants.HOME_API_VERSION_2)
    fun provideHomeApi2(@Named(AppConstants.API_VERSION_2) retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

}