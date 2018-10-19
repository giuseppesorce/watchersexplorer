package com.giuseppesorce.watchersexplorer.android.ui.homesearch



import android.util.Log
import com.giuseppesorce.common.addDisposableTo
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchParameters
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchUseCases
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class HomePresenter @Inject constructor(private val searchUseCases: SearchUseCases) : Presenter<HomeView> {



    private var view: HomeView? = null
    private var compositeDisposable = CompositeDisposable()

    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    override fun attachView(view: HomeView) {
        this.view = view
    }

    fun searchRepo(word: String) {

        searchUseCases.execute(SearchParameters(word)).subscribe({ data ->
            Log.i("watcher", "Repo: "+data.size)

                 }, { error ->
                   Log.e("watcher", "ERRORE: "+error)
                   Log.e("watcher", "ERRORE: "+error.toString())

                 }).addDisposableTo(compositeDisposable)


    }


}