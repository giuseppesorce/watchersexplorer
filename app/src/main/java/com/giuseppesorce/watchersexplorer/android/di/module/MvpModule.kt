package com.giuseppesorce.move.android.di.module


import com.giuseppesorce.watchersexplorer.android.mvp.PresenterProvider
import dagger.Module


/**
 * @author Giuseppe Sorce
 */

@Module
class MvpModule(private val presenterProvider: PresenterProvider) {


//    @Provides
//    fun startPresenter(signinCase: SigninCase): StartPresenter {
//        val presenter = presenterProvider.getRetainedPresenter() as? StartPresenter
//        return presenter
//            ?: StartPresenter(signinCase)
//    }



}