package com.example.data.mapper

import com.example.base.ApiMapper
import com.example.data.network.models.ApiListItem
import com.example.domain.models.FetchItem
import com.example.domain.models.FetchLists

class ApiFetchListMapper: ApiMapper<List<ApiListItem?>?, FetchLists> {
    override fun mapToDomain(apiEntity: List<ApiListItem?>?): FetchLists {
        val map = mutableMapOf<Int, MutableList<FetchItem>>()
        apiEntity?.forEach { apiListItem ->
            val fetchItem = apiListItem?.let itemLet@{
                FetchItem(
                    id = it.id ?: return@itemLet null,
                    name = it.name.takeIf { name -> !name.isNullOrBlank() } ?: return@itemLet null,
                    listId = it.listId ?: return@itemLet null,
                )
            }

            fetchItem?.let { item ->
                val newList = map.getOrDefault(item.listId, mutableListOf())
                newList.add(item)
                map[item.listId] = newList
            }
        }

        map.forEach { (_, list) ->
            list.sortBy { item -> item.id }
        }

        return FetchLists(
            mapOfLists = map.toSortedMap()
        )
    }
}