package com.alex.microprojectmvvm.model.realm

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class FootballMatch (
    @PrimaryKey
    var title: String = "",
    var embed: String = "",
    var url: String = "",
    var thumbnail: String = "",
    var date: Date? = null,
    var side1: TeamSide? = null,
    var side2: TeamSide? = null,
    var competition: FootballCompetition? = null,
    var videos: RealmList<FootballMatchVideo> = RealmList()
) : RealmModel