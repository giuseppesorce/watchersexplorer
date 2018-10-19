package com.giuseppesorce.watchersexplorer.android.ui.homesearch

import android.os.Bundle
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.mvp.MvpActivity
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import javax.inject.Inject

class HomeSearchActivity : MvpActivity(), HomeView {
    override fun showMessage(message: String) {


    }

    @Inject
    lateinit var presenter: HomePresenter

    override fun getPresenter(): Presenter<*> = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        presenter.searchRepo("clean")
    }


    override fun onInject() {
        super.onInject()
        mvpComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
