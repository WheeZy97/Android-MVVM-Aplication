package com.alex.microprojectmvvm.presentation.ui.matches

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.microprojectmvvm.api.service.FootballService
import com.alex.microprojectmvvm.model.database.AppDatabase
import com.alex.microprojectmvvm.model.FootballMatch
import com.alex.microprojectmvvm.presentation.adapter.FootballMatchesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class FootballMatchesViewModel(private val footballApi: FootballService, private val appDatabase: AppDatabase,
                                private val compositeDisposable: CompositeDisposable) : ViewModel() {

    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val footballMatchesLiveData: LiveData<List<FootballMatch>>

    val footballMatchesAdapter: FootballMatchesAdapter

    val errorClickListener = View.OnClickListener { fetchFootballMatchesData() }

    init {
        footballMatchesAdapter = FootballMatchesAdapter(listOf())
        footballMatchesLiveData = appDatabase.footballMatchDao().getMatchesList()
    }

    fun fetchFootballMatchesData() {
        val disposable = footballApi.getFootballMatches()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                onRetrieveFootballMatchesStart()
            }
            .subscribe({ footballMatches ->
                appDatabase.queryExecutor.execute {
                    appDatabase.footballMatchDao().insertMatches(footballMatches)
                }
            }, {
                onRetrieveFootballMatchesListError(it)
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun onRetrieveFootballMatchesStart() {
        errorMessage.value = null
    }

    private fun onRetrieveFootballMatchesListError(error: Throwable) {
        errorMessage.value = error.localizedMessage
    }
}