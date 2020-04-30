package com.alex.microprojectmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FootballCompetition (
    @ColumnInfo(name = "competitionName")
    var name: String = "",
    @field:PrimaryKey
    @ColumnInfo(name = "competitionId")
    var id: Int = -1,
    var url: String = ""
)