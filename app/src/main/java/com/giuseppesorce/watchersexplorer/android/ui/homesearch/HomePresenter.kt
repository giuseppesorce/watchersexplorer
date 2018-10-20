package com.giuseppesorce.watchersexplorer.android.ui.homesearch


import android.util.Log
import com.giuseppesorce.common.addDisposableTo
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchParameters
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchRepoUseCases
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchSubcribersUseCases
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchSubscribersParameters
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class HomePresenter @Inject constructor(
    private val searchUseCases: SearchRepoUseCases,
    private val searchSubcribersUseCases: SearchSubcribersUseCases
) :
    Presenter<HomeView> {


    private var view: HomeView? = null
    private var compositeDisposable = CompositeDisposable()
    private val MIN_CHARS: Int = 3

    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    override fun attachView(view: HomeView) {
        this.view = view
        view.setupView()
    }

    private fun searchRepo(word: String) {

        searchUseCases.execute(SearchParameters(word)).subscribe({ repo ->

            view?.updateRepoList(repo)

        }, { error ->

            Log.e("watcher", "ERRORE: " + error.toString())

        }).addDisposableTo(compositeDisposable)
    }

    fun searchSubscribers(owner: String, repo: String) {

        searchSubcribersUseCases.execute(SearchSubscribersParameters(owner, repo)).subscribe({ data ->
            Log.i("watcher", "Sub: " + data.size)

        }, { error ->
            Log.e("watcher", "ERRORE: " + error)
            Log.e("watcher", "ERRORE: " + error.toString())

        }).addDisposableTo(compositeDisposable)


    }


    fun onSubmitSearch(query: String) {
        if (query.isNullOrEmpty() || query.length < MIN_CHARS) {
            view?.showMessage("Error min chars is...")
        } else {
            searchRepo(query)
        }

    }

    fun onQueryTextChangeSearch(query: String) {

    }


}