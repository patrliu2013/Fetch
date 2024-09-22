package com.example.data.usecase

import com.example.base.ResultWrapper
import com.example.domain.models.FetchLists
import com.example.domain.repository.FetchRepository
import com.example.domain.usecase.GetFetchListUseCase

class GetFetchListUseCaseImpl(
    private val fetchRepository: FetchRepository
): GetFetchListUseCase {
    override suspend fun invoke(): ResultWrapper<FetchLists> {
        return fetchRepository.getList()
    }
}