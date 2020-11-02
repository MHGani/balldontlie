package com.example.balldontlie.di

import com.example.balldontlie.viewmodel.PlayersListViewModel
import com.example.balldontlie.viewmodel.TeamsListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { PlayersListViewModel(get()) }
    viewModel { TeamsListViewModel(get()) }
}

