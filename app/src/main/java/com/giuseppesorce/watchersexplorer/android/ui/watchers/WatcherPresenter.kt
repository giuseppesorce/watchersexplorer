package com.giuseppesorce.watchersexplorer.android.ui.watchers


import android.util.Log
import com.giuseppesorce.common.addAnotherDisposableTo
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
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


    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    override fun attachView(view: WatchersView) {
        this.view = view
        view.setupView()
        view.showHideProgress(true)
    }

    private fun searchWatchers(owner: String, repo: String) {

        searchSubcribersUseCases.execute(SearchSubscribersParameters(owner = owner, repo = repo))
            .subscribe({ watchers ->
                view?.updateWatchers(watchers)
            }, { error ->

                view?.showHideAlertMessage(true)
                view?.showHideProgress(false)
                view?.showMessage(view?.getStr(R.string.noConnection) ?: "")

            }).addAnotherDisposableTo(compositeDisposable)
    }

    private var owner: String = ""
    private var repo: String = ""

    fun setOwner(owner: String) {

        this.owner = owner
    }

    fun setRepo(repo: String) {
        this.repo = repo
    }

    fun loadWatchers() {
        if (!owner.isEmpty() && !repo.isEmpty()) {
            view?.showHideProgress(true)
            searchWatchers(owner, repo)
        } else {
            //TODO show error in activity
        }

    }

    fun setOnBack() {
        //so i can close or cancel view or process before finish() activity
        view?.closeActivity()

    }


}
