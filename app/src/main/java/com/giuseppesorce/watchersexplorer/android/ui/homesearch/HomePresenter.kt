package com.giuseppesorce.watchersexplorer.android.ui.homesearch



import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class HomePresenter @Inject constructor() : Presenter<HomeView> {



    private var view: HomeView? = null
    private var compositeDisposable = CompositeDisposable()

    override fun detachView() {
        view = null
    }



    override fun attachView(view: HomeView) {
        this.view = view


    }




}