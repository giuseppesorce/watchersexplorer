package com.giuseppesorce.watchersexplorer.data.api.repositories

import com.giuseppesorce.watchersexplorer.data.api.SearchApi
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SearchRepoResponsePayLoad
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SearchSubscribersRepoResponsePayLoad
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */

class SearchRepository @Inject constructor(private val searchApi: SearchApi) {


    fun searchRepo(word :String?): Single<SearchRepoResponsePayLoad> {
        return searchApi.searchRepository(word ?: "")
    }

    fun searchSubscribers(owner :String, repo:String): Single<SearchSubscribersRepoResponsePayLoad> {
        return searchApi.searchSubscribers(owner, repo)
    }




}