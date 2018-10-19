package com.giuseppesorce.watchersexplorer.domain.mappers

import com.giuseppesorce.watchersexplorer.data.api.models.responses.Repo
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SerachRepoResponsePayLoad
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class SearchMapper @Inject constructor() {
    fun getRepo(it: SerachRepoResponsePayLoad): List<Repo>? {
        return  emptyList<Repo>()
    }

}

