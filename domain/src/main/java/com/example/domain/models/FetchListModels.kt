package com.example.domain.models

data class FetchLists(
    val mapOfLists: Map<Int, List<FetchItem>> // Map<listId, list>
)

data class FetchItem(
    val id: Int,
    val name: String,
    val listId: Int,
)