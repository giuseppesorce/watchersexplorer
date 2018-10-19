package com.giuseppesorce.move.android.di.module


import com.giuseppesorce.watchersexplorer.android.mvp.PresenterProvider
import com.giuseppesorce.watchersexplorer.android.ui.homesearch.HomePresenter
import dagger.Module
import dagger.Provides


/**
 * @author Giuseppe Sorce
 */

@Module
class MvpModule(private val presenterProvider: PresenterProvider) {


    @Provides
    fun homePresenter(): HomePresenter {
        val presenter = presenterProvider.getRetainedPresenter() as? HomePresenter
        return presenter
            ?: HomePresenter()
    }



}