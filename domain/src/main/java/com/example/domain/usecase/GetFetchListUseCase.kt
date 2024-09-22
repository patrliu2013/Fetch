package com.example.domain.usecase

import com.example.base.ResultWrapper
import com.example.domain.models.FetchLists

interface GetFetchListUseCase {
    suspend fun invoke(): ResultWrapper<FetchLists>
}