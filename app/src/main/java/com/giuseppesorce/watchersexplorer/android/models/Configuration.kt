package com.giuseppesorce.watchersexplorer.android.models

import com.giuseppesorce.watchersexplorer.BuildConfig
import dagger.Reusable
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
@Reusable
class Configuration @Inject constructor() {


    val moveBaseUrl: String by lazy {
        "https://api.github.com"
    }

    val requestTimeoutSeconds: Long = 5

    fun isDebug() = BuildConfig.DEBUG

    fun isProduction() = !BuildConfig.DEBUG
}
