package com.example.modules

import com.example.data.usecase.GetFetchListUseCaseImpl
import com.example.domain.usecase.GetFetchListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetFetchListUseCase> { GetFetchListUseCaseImpl(get()) }
}