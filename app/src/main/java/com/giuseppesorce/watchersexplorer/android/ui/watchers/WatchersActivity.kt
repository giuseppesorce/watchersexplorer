package com.giuseppesorce.watchersexplorer.android.ui.watchers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.mvp.MvpActivity
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.android.ui.adapters.RepoWatchersAdapter
import com.giuseppesorce.watchersexplorer.data.api.models.RepoWatcher
import kotlinx.android.synthetic.main.activity_homesearch.*
import javax.inject.Inject

class WatchersActivity : MvpActivity(), WatchersView {


    // inject presenter
    @Inject
    lateinit var presenter: WatcherPresenter
    // create adapter
    private val repoWatchersAdapter: RepoWatchersAdapter by lazy {
        RepoWatchersAdapter()
    }

    private lateinit var mainMenu: Menu

    private val listLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homesearch)
        intent?.getStringExtra(WatchersActivity.OWNER)?.let {
            presenter.setOwner(it)
        }
        intent?.getStringExtra(WatchersActivity.REPO)?.let {
            presenter.setRepo(it)
        }
        presenter.attachView(this)
        presenter.loadWatchers()
    }

    override fun setupView() {
        rvList.layoutManager = listLayoutManager
        rvList.adapter = repoWatchersAdapter
    }

    override fun updateWatchers(watchers: List<RepoWatcher>) {
        repoWatchersAdapter.allWatchers= watchers
    }

    override fun showMessage(message: String) {

    }

    companion object {
        var OWNER: String = "owner"
        var REPO: String = "repo"
        fun newIntent(context: Context, owner: String,  repo: String ): Intent {
            val intent = Intent(context, WatchersActivity::class.java)

            intent.putExtra(OWNER, owner)
            intent.putExtra(REPO, repo)
            return intent
        }
    }

    override fun getPresenter(): Presenter<*> = presenter

    // inject activity
    override fun onInject() {
        super.onInject()
        mvpComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
