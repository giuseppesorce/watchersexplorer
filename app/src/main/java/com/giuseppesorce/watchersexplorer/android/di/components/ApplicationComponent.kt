package com.giuseppesorce.watchersexplorer.android.di.components


import android.content.Context
import com.giuseppesorce.common.executors.MainThreadExecutor
import com.giuseppesorce.common.executors.WorkerThreadExecutor
import com.giuseppesorce.watchersexplorer.android.BaseActivity
import com.giuseppesorce.watchersexplorer.android.di.module.ApplicationModule
import com.giuseppesorce.watchersexplorer.android.di.module.NetworkModule
import com.giuseppesorce.watchersexplorer.android.models.HeadersConfiguration
import com.giuseppesorce.watchersexplorer.data.api.SearchApi
import com.giuseppesorce.xwatchersexplorer.android.di.module.DaoModule
import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * @author Giuseppe Sorce
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
    NetworkModule::class, DaoModule::class))
interface ApplicationComponent {

    fun inject(activity: BaseActivity)

    fun context(): Context
    fun gson(): Gson



    fun looperThreadExecutor(): WorkerThreadExecutor

    fun mainThreadExecutor(): MainThreadExecutor

    fun headersConfiguration(): HeadersConfiguration

    fun okHttpClient(): OkHttpClient

    /** DAO **/

    //fun userDao():UsersDao



    /** API **/

    fun searchApi(): SearchApi





}




