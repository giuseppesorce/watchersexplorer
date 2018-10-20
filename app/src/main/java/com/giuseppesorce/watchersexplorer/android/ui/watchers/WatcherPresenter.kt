package com.giuseppesorce.watchersexplorer.android.ui.watchers


import android.util.Log
import com.giuseppesorce.common.addDisposableTo
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.android.ui.homesearch.HomeView
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchParameters
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchRepoUseCases
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchSubcribersUseCases
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchSubscribersParameters
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class WatcherPresenter @Inject constructor(
    private val searchSubcribersUseCases: SearchSubcribersUseCases
) :
    Presenter<WatchersView> {


    private var view: WatchersView? = null
    private var compositeDisposable = CompositeDisposable()
    private val MIN_CHARS: Int = 3

    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

        override fun attachView(view: WatchersView) {
        this.view = view
        view.setupView()
    }

    private fun searchRepo(word: String) {

        searchSubcribersUseCases.execute().subscribe({ repo ->



        }, { error ->

            Log.e("watcher", "ERRORE: " + error.toString())

        }).addDisposableTo(compositeDisposable)
    }




}
