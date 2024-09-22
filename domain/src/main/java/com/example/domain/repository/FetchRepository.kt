package com.example.domain.repository

import com.example.base.ResultWrapper
import com.example.domain.models.FetchLists

interface FetchRepository {
    suspend fun getList(): ResultWrapper<FetchLists>
}