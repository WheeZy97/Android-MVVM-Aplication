package com.alex.microprojectmvvm.di.component

import com.alex.microprojectmvvm.di.module.*
import com.alex.microprojectmvvm.presentation.ui.match.FootballMatchInfoActivity
import com.alex.microprojectmvvm.presentation.ui.matches.FootballMatchesActivity
import com.alex.microprojectmvvm.presentation.ui.matches.FootballMatchesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class, RealmModule::class, DisposableModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: FootballMatchInfoActivity)
    fun inject(activity: FootballMatchesActivity)
}