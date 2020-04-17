package com.alex.microprojectmvvm.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alex.microprojectmvvm.api.service.FootballService
import com.alex.microprojectmvvm.manager.RealmDataManager
import com.alex.microprojectmvvm.presentation.ui.matches.FootballMatchesViewModel
import io.reactivex.disposables.CompositeDisposable

class ViewModelFactory(var footballApi: FootballService, var realmDataManager: RealmDataManager,
                       var compositeDisposable: CompositeDisposable) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FootballMatchesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FootballMatchesViewModel(footballApi, realmDataManager, compositeDisposable) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}