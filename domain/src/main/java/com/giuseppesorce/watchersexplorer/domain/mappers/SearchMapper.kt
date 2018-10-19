package com.giuseppesorce.watchersexplorer.domain.mappers

import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import com.giuseppesorce.watchersexplorer.data.api.models.RepoSubscriber
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SearchRepoResponsePayLoad
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SearchSubscribersRepoResponsePayLoad
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class SearchMapper @Inject constructor() {
    fun getRepo(it: SearchRepoResponsePayLoad): List<Repo>? {
        return  emptyList<Repo>()
    }

    fun getSubscribers(it: SearchSubscribersRepoResponsePayLoad): List<RepoSubscriber>? {
        return  emptyList<RepoSubscriber>()

    }

}

