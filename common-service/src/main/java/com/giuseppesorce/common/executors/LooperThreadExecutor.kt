package com.giuseppesorce.common.executors

import android.os.Handler
import android.os.HandlerThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author Giuseppe Sorce on 25/10/17.
 */
class LooperThreadExecutor @Inject constructor() : Executor, RxExecutor {

    private val thread: HandlerThread by lazy {
        val thread = HandlerThread("LooperThreadExecutor")
        thread.setUncaughtExceptionHandler { t, e ->
            Timber.e(e, "Uncaught uncaught exception. Cause: %s", e.cause?.localizedMessage ?: "N.D.")
        }
        thread.start()
        thread
    }

    private val handler: Handler by lazy {
        Handler(thread.looper)
    }

    override fun execute(command: Runnable?) {
        handler.post(command)
    }

    override val scheduler: Scheduler
        get() = Schedulers.from(this)
}