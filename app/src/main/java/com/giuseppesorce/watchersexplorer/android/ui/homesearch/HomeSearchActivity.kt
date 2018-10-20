package com.giuseppesorce.watchersexplorer.android.ui.homesearch

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.mvp.MvpActivity
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter

import kotlinx.android.synthetic.main.c_toolbar.*
import javax.inject.Inject

class HomeSearchActivity : MvpActivity(), HomeView {
    override fun showMessage(message: String) {


    }

    @Inject
    lateinit var presenter: HomePresenter

    override fun getPresenter(): Presenter<*> = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homesearch)
        presenter.attachView(this)
        //setup toolbar
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

    private fun setupSearch() {
        var searchView = MenuItemCompat.getActionView(mainMenu.findItem(R.id.menu_search)) as SearchView
        val item = mainMenu.findItem(R.id.menu_search)
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


    override fun onInject() {
        super.onInject()
        mvpComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
