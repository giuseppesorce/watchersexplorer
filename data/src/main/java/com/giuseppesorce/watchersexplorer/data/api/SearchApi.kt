package com.giuseppesorce.watchersexplorer.data.api

import com.giuseppesorce.watchersexplorer.data.api.models.responses.SerachRepoResponsePayLoad
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author Giuseppe Sorce
 */
interface SearchApi {


    @GET("/rest/auth/signup")
     fun searchRepository(): Single<SerachRepoResponsePayLoad>

}