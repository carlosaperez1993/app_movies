package com.app.movieapp.di

import com.app.movieapp.data.repository.remote.NetworkInject
import com.app.movieapp.data.db.DatabaseInjector
import com.app.movieapp.data.repository.MoviesRepository
import com.app.movieapp.data.repository.local.LocalDataSource
import com.app.movieapp.data.repository.remote.RemoteDataSource
import com.app.movieapp.data.viewmodels.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module(override = true) {

    single { NetworkInject().provideAPI() }

    single { DatabaseInjector().provideAppDatabase(get()) }

}

val dataModule = module{

    single { LocalDataSource(get()) }

    single { RemoteDataSource(get()) }

    single { MoviesRepository(get(),get())}
}

val viewmodelModule = module {
    viewModel { AppViewModel(get()) }
}