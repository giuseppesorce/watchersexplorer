package com.giuseppesorce.watchersexplorer.data.api

import com.giuseppesorce.watchersexplorer.data.api.models.responses.SerachRepoResponsePayLoad
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Giuseppe Sorce
 */
interface SearchApi {


    @GET("search/repositories")
    fun searchRepository(@Query("q") word: String): Single<SerachRepoResponsePayLoad>

}