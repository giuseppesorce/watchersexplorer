package com.giuseppesorce.watchersexplorer.android.ui.homesearch

import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchParameters
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchRepoUseCases
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchSubcribersUseCases
import io.reactivex.Single
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*

class HomePresenterTest {

    private val searchUseCases = mock(SearchRepoUseCases::class.java)
    private val searchSubcribersUseCases = mock(SearchSubcribersUseCases::class.java)

    private val presenter = HomePresenter(searchUseCases, searchSubcribersUseCases)

    @Test
    fun `setup HomeView when attached`() {
        val view = mock(HomeView::class.java)

        presenter.attachView(view)

        verify(view).setupView()
    }

    @Test
    fun `show error message if query is empty`() {
        val view = mock(HomeView::class.java)

        presenter.attachView(view)
        presenter.onSubmitSearch("")

        verify(view).showMessage(anyString())
    }

    @Test
    fun `show error message if query is shorter than 3 chars`() {
        val view = mock(HomeView::class.java)

        presenter.attachView(view)
        presenter.onSubmitSearch("aa")

        verify(view).showMessage(anyString())
    }

    @Test
    fun `start repo search if query is at least 3 chars long`() {
        given(searchUseCases.execute(any(SearchParameters::class.java))).willReturn(Single.never())
        val view = mock(HomeView::class.java)
        val query = "aaa"
        presenter.attachView(view)
        presenter.onSubmitSearch(query)

        verify(searchUseCases).execute(SearchParameters(query))
    }

    @Test
    fun `don't update repo list if search doesn't return`() {
        val query = "aaa"
        given(searchUseCases.execute(SearchParameters(query))).willReturn(Single.never())
        val view = mock(HomeView::class.java)

        presenter.attachView(view)
        presenter.onSubmitSearch(query)

        verify(view, never()).updateRepoList(anyList())
    }

    @Test
    fun `update repo list if search returns`() {
        val query = "aaa"
        val resultList = emptyList<Repo>()
        given(searchUseCases.execute(SearchParameters(query))).willReturn(Single.just(resultList))
        val view = mock(HomeView::class.java)

        presenter.attachView(view)
        presenter.onSubmitSearch(query)

        verify(view).updateRepoList(resultList)
    }
}
