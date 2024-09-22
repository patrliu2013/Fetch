package com.example.data.repository

import com.example.base.ApiMapper
import com.example.base.ResultWrapper
import com.example.data.network.FetchApiService
import com.example.data.network.models.ApiListItem
import com.example.domain.models.FetchLists
import com.example.domain.repository.FetchRepository

class FetchRepositoryImpl(
    private val fetchApiService : FetchApiService,
    private val apiFetchListMapper: ApiMapper<List<ApiListItem?>?, FetchLists>
): FetchRepository {
    override suspend fun getList(): ResultWrapper<FetchLists> {
        return try {
            val response = fetchApiService.getList()
            if (response.isSuccessful) {
                val body = response.body()
                body?.run {
                    val mappedObject = apiFetchListMapper.mapToDomain(body)
                    mappedObject.mapOfLists.takeIf { it.isNotEmpty() }?.let {
                        ResultWrapper.Success(mappedObject)
                    } ?: ResultWrapper.SuccessNoData
                } ?: run {
                    ResultWrapper.SuccessNoData
                }
            } else {
                ResultWrapper.GenericError(Exception("No Success"))
            }
        } catch (ex: Exception) {
            ResultWrapper.GenericError(ex)
        }
    }
}