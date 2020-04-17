package com.alex.microprojectmvvm.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class DisposableModule {

    @Provides
    @Singleton
    fun provideCompositeDisposable() : CompositeDisposable =
        CompositeDisposable()
}