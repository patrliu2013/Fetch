package com.example.fetch.di

import com.example.fetch.ui.list.FetchListViewModel
import com.example.fetch.ui.welcome.FetchWelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FetchWelcomeViewModel() }
    viewModel { FetchListViewModel(get()) }
}