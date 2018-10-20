package com.giuseppesorce.watchersexplorer.android.ui.homesearch

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.mvp.MvpActivity
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.android.ui.homesearch.adapters.RepoListAdapter
import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import kotlinx.android.synthetic.main.activity_homesearch.*
import javax.inject.Inject

class HomeSearchActivity : MvpActivity(), HomeView {


    @Inject
    lateinit var presenter: HomePresenter

    private val repoListAdapter: RepoListAdapter by lazy {
        RepoListAdapter()
    }

    private val listLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    override fun getPresenter(): Presenter<*> = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homesearch)
        presenter.attachView(this)
        // setup toolbar
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

    }

    private lateinit var mainMenu: Menu

    @Override
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater?.inflate(R.menu.home, menu)
        mainMenu = menu
        setupSearch()
        return true
    }

    /**
     * setup seach action; submit and change text
     */
    private fun setupSearch() {
        var searchView = MenuItemCompat.getActionView(mainMenu.findItem(R.id.menu_search)) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.onSubmitSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.onQueryTextChangeSearch(newText)
                return false
            }
        })
    }

    override fun updateRepoList(reposList: List<Repo>) {

        repoListAdapter.allRepos = reposList
    }

    override fun setupView() {
        rvList.layoutManager = listLayoutManager
        rvList.adapter = repoListAdapter
    }

    override fun showMessage(message: String) {


    }

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
