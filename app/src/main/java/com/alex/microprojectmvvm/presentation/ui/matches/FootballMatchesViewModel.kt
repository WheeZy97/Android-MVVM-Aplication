package com.alex.microprojectmvvm.presentation.ui.matches

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.microprojectmvvm.api.service.FootballService
import com.alex.microprojectmvvm.manager.RealmDataManager
import com.alex.microprojectmvvm.model.realm.FootballMatch
import com.alex.microprojectmvvm.presentation.adapter.FootballMatchesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.RealmResults
import javax.inject.Inject

class FootballMatchesViewModel(private val footballApi: FootballService, private val realmDataManager: RealmDataManager,
                                private val compositeDisposable: CompositeDisposable) : ViewModel() {

    val footballMatchesAdapter: FootballMatchesAdapter
    val footballMatches: RealmResults<FootballMatch>

    val errorMessage:MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { fetchFootballMatchesData() }

    init {
        footballMatches = realmDataManager.getFootballMatches()
        footballMatchesAdapter = FootballMatchesAdapter(footballMatches)
    }

    fun fetchFootballMatchesData() {
        val disposable = footballApi.getFootballMatches()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                onRetrieveFootballMatchesStart()
            }
            .subscribe({ footballMatches ->
                realmDataManager.saveFootballMatchesToRealm(footballMatches)
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