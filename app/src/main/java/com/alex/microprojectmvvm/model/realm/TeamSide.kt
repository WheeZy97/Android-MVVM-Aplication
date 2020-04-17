package com.alex.microprojectmvvm.model.realm

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class TeamSide (
    var name: String = "",
    var url: String = ""
) : RealmModel