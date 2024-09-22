package com.example.modules

import com.example.base.ApiMapper
import com.example.data.mapper.ApiFetchListMapper
import com.example.data.network.models.ApiListItem
import com.example.domain.models.FetchLists
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiMapperModule = module {
    single(named("ApiFetchListMapper")) { ApiFetchListMapper() as ApiMapper<List<ApiListItem?>?, FetchLists> }
}