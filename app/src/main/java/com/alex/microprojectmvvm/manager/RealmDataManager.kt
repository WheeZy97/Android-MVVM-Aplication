package com.alex.microprojectmvvm.manager

import com.alex.microprojectmvvm.model.realm.FootballMatch
import io.realm.Realm
import io.realm.RealmResults

class RealmDataManager(val realm: Realm) {

    fun saveFootballMatchesToRealm(footballMatches: List<FootballMatch>) {
        realm.executeTransaction {
            realm.copyToRealmOrUpdate(footballMatches)
        }
    }

    fun getFootballMatches() : RealmResults<FootballMatch> =
        realm.where(FootballMatch::class.java).findAll()

    fun getFootballMatch(footballMatchName: String?) : FootballMatch? =
        realm.where(FootballMatch::class.java).equalTo("title", footballMatchName).findFirst()
}