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
    fun getRepo(response: SearchRepoResponsePayLoad): List<Repo> {


        return response.items?.let {

            it.map {
                Repo(
                    it.id ?: 0,
                    it.watchers_count ?: 0,
                    it.name,
                    it.description,
                    it.language ?: "",
                    it.private,
                    it.forks_count,
                    it.owner?.id ?: 0,
                    it.owner?.login,
                    it.owner?.avatar_url,
                    it.owner?.repos_url,
                    it.owner?.login
                )
            }

        } ?: emptyList<Repo>()

    }

    fun getSubscribers(it: SearchSubscribersRepoResponsePayLoad): List<RepoSubscriber>? {
        return emptyList<RepoSubscriber>()

    }

}

