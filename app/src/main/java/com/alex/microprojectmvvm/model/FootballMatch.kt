package com.alex.microprojectmvvm.model

import androidx.room.*
import java.util.*


@Entity
data class FootballMatch (
    @field:PrimaryKey
    var title: String = "",
    var embed: String = "",
    @ColumnInfo(name = "match_url")
    var url: String = "",
    var thumbnail: String = "",
    var date: Date? = null,
    @Embedded
    var side1: TeamSideOne? = null,
    @Embedded
    var side2: TeamSideTwo? = null,
    @Embedded
    var competition: FootballCompetition? = null,
    var videos: List<FootballMatchVideo> = listOf()
)