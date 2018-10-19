package com.giuseppesorce.watchersexplorer.domain.interactors

import com.giuseppesorce.common.executors.MainThreadExecutor
import com.giuseppesorce.common.executors.WorkerThreadExecutor
import com.giuseppesorce.watchersexplorer.data.api.models.responses.Repo
import com.giuseppesorce.watchersexplorer.data.api.repositories.SearchRepository
import com.giuseppesorce.watchersexplorer.domain.mappers.SearchMapper
import io.reactivex.Single
import javax.inject.Inject


/**
 * @author Giuseppe Sorce
 */

class SearchUseCases @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchRepository: SearchRepository, workerThreadExecutor: WorkerThreadExecutor,
    mainThreadExecutor: MainThreadExecutor) : UseCaseSingle<List<Repo>, SearchParameters>(workerThreadExecutor, mainThreadExecutor) {
    override fun useCaseObservable(params: SearchParameters?): Single<List<Repo>> {
        return searchRepository.searchRepo(params?.scopename ).map { searchMapper.getRepo(it) }
    }
}

data class SearchParameters(
    val scopename: String
) : UseCaseParams



