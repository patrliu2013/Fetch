package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class ApiListItem(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("listId")
    val listId: Int? = null,
    @SerializedName("name")
    val name: String? = null,
)