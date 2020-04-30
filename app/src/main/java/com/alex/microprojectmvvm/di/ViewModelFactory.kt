package com.alex.microprojectmvvm.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alex.microprojectmvvm.api.service.FootballService
import com.alex.microprojectmvvm.model.database.AppDatabase
import com.alex.microprojectmvvm.presentation.ui.match.FootballMatchInfoViewModel
import com.alex.microprojectmvvm.presentation.ui.matches.FootballMatchesViewModel
import io.reactivex.disposables.CompositeDisposable


class ViewModelFactory(var footballApi: FootballService, var appDatabase: AppDatabase,
                       var compositeDisposable: CompositeDisposable) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FootballMatchesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FootballMatchesViewModel(footballApi, appDatabase, compositeDisposable) as T
        } else if (modelClass.isAssignableFrom(FootballMatchInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FootballMatchInfoViewModel(appDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}