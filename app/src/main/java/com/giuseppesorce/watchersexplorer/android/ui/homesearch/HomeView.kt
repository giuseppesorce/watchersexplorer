package com.giuseppesorce.watchersexplorer.android.ui.homesearch

import com.giuseppesorce.watchersexplorer.android.mvp.CView
import com.giuseppesorce.watchersexplorer.data.api.models.Repo


/**
 * @author Giuseppe Sorce
 */
interface HomeView : CView {
    fun updateRepoList(data: List<Repo>)
    fun setupView()
     fun showWatchers(name: String, nameOwner: String)
    fun getStr(stringResId: Int): String
    fun showHideAlertMessage(isShow:Boolean)
    fun showHideProgress(isShow: Boolean)

}