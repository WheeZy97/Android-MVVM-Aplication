package com.alex.microprojectmvvm.di.module

import androidx.lifecycle.ViewModelProvider
import com.alex.microprojectmvvm.api.service.FootballService
import com.alex.microprojectmvvm.di.ViewModelFactory
import com.alex.microprojectmvvm.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [RemoteModule::class, RoomModule::class, DisposableModule::class])
class ViewModelModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(footballApi: FootballService, appDataBase: AppDatabase,
                                compositeDisposable: CompositeDisposable) : ViewModelProvider.Factory =
        ViewModelFactory(footballApi, appDataBase, compositeDisposable)
}