package com.example.modules

import com.example.data.repository.FetchRepositoryImpl
import com.example.domain.repository.FetchRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single {
        FetchRepositoryImpl(
            fetchApiService = get(),
            apiFetchListMapper = get(named("ApiFetchListMapper"))
        ) as FetchRepository
    }
}