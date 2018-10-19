package com.giuseppesorce.watchersexplorer.android.di.module

import android.util.Log
import com.giuseppesorce.watchersexplorer.data.api.SearchApi
import com.giuseppesorce.watchersexplorer.android.models.AuthHeaderInterceptor
import com.giuseppesorce.watchersexplorer.android.models.Configuration
import com.giuseppesorce.watchersexplorer.android.models.HeadersConfiguration
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 *
 */
@Module
class NetworkModule(val cacheSize: Long = 10 * 1024 * 1024, val cacheDir: File) {

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    }

    @Provides
    @Singleton
    fun provideHeadersConfiguration(): HeadersConfiguration {
        return HeadersConfiguration()
    }

    //
    @Provides
    @Singleton
    fun providesOkHttpClient(authHeaderInterceptor: AuthHeaderInterceptor): OkHttpClient {
        var builder = OkHttpClient.Builder()

            .connectTimeout(160, TimeUnit.SECONDS)
            .readTimeout(160, TimeUnit.SECONDS)
            .writeTimeout(160, TimeUnit.SECONDS)

        if (cacheDir != null) {
            val cache = Cache(cacheDir, cacheSize)
            builder.cache(cache)
        }
        builder.interceptors().add(authHeaderInterceptor)

        // certificate pinning
        return builder.build()
    }

    //
    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.i("logserver: ", message)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    //
    @Provides
    @Singleton
    @Named("staging")
    internal fun provideApiStagingRetrofit(
        okHttpClient: OkHttpClient,
        configuration: Configuration
    ): Retrofit {

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ")
            .create()
        return Retrofit.Builder()
            .baseUrl(configuration.moveBaseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

            .build()
    }

    @Provides
    @Singleton
    fun provideSearchApi(@Named("staging") retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }


//    @Provides
//    @Singleton
//    fun provideEnviroment(): CEnvironment {
//        return CEnvironment("", "")
//    }
}