package com.giuseppesorce.watchersexplorer.android.ui.homesearch


import android.util.Log
import com.giuseppesorce.common.addAnotherDisposableTo
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchParameters
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchRepoUseCases
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class HomePresenter @Inject constructor(
    private val searchUseCases: SearchRepoUseCases

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

        }).addAnotherDisposableTo(compositeDisposable)
    }




    fun onSubmitSearch(query: String) {
        if (query.isEmpty() || query.length < MIN_CHARS) {
            view?.showMessage("Error min chars is...")
        } else {
            searchRepo(query)
        }

    }

    fun onQueryTextChangeSearch(query: String) {

    }

    fun onSelectRepo(repo: Repo?) {

        repo?.let {
            var name : String = it.name ?: ""
            var nameOwner : String = it.nameOwner ?: ""
            if(!name.isEmpty() && !nameOwner.isEmpty()){
                view?.showWatchers(name  ,nameOwner)
            }

        }

    }


}
