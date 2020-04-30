package com.alex.microprojectmvvm.presentation.ui.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alex.microprojectmvvm.model.FootballMatch
import com.alex.microprojectmvvm.model.database.AppDatabase

class FootballMatchInfoViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private var matchTitle: String = ""
    lateinit var footballMatch: LiveData<FootballMatch>

    fun setTitle(matchTitle: String?) {
        if (matchTitle == null) return

        this.matchTitle = matchTitle
    }

    fun retrieveFootballMatchInfoFromAppDatabase() {
        footballMatch = appDatabase.footballMatchDao().getFootballMatchByTitle(matchTitle)
    }

}