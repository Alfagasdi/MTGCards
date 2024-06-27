package com.example.mtgcards.base.di

import com.example.mtgcards.components.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeScreenViewModel() }
}