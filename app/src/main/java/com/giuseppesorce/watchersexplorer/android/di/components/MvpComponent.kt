package com.giuseppesorce.watchersexplorer.android.di.components

import com.giuseppesorce.move.android.di.module.MvpModule
import com.giuseppesorce.watchersexplorer.android.di.ActivityScope
import com.giuseppesorce.watchersexplorer.android.ui.homesearch.HomeSearchActivity
import dagger.Component

/**
 * @author Giuseppe Sorce
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class),
    modules = arrayOf(MvpModule::class))

interface MvpComponent {

   fun inject(homeSearchActivity: HomeSearchActivity)


}