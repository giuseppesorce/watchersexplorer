package com.giuseppesorce.watchersexplorer.data.api.repositories

import com.giuseppesorce.watchersexplorer.data.api.SearchApi
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SerachRepoResponsePayLoad
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */

class SearchRepository @Inject constructor(private val searchApi: SearchApi) {


    fun searchRepo(word :String?): Single<SerachRepoResponsePayLoad> {
        return searchApi.searchRepository(word ?: "")
    }




}