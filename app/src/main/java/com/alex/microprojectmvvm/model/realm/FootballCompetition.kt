package com.alex.microprojectmvvm.model.realm

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class FootballCompetition (
    var name: String = "",
    @PrimaryKey
    var id: Int = -1,
    var url: String = ""
) : RealmModel