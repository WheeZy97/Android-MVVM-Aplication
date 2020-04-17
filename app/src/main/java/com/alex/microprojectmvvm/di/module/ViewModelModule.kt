package com.alex.microprojectmvvm.di.module

import androidx.lifecycle.ViewModelProvider
import com.alex.microprojectmvvm.api.service.FootballService
import com.alex.microprojectmvvm.di.ViewModelFactory
import com.alex.microprojectmvvm.manager.RealmDataManager
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [RemoteModule::class, RealmModule::class, DisposableModule::class])
class ViewModelModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(footballApi: FootballService, realmDataManager: RealmDataManager,
                                compositeDisposable: CompositeDisposable) : ViewModelProvider.Factory =
        ViewModelFactory(footballApi, realmDataManager, compositeDisposable)
}