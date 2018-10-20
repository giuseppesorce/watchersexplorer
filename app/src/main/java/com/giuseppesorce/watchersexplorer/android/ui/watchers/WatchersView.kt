package com.giuseppesorce.watchersexplorer.android.ui.watchers

import com.giuseppesorce.watchersexplorer.android.mvp.CView
import com.giuseppesorce.watchersexplorer.data.api.models.RepoWatcher


/**
 * @author Giuseppe Sorce
 */
interface WatchersView : CView {
    fun setupView()
    fun updateWatchers(watchers: List<RepoWatcher>)

}