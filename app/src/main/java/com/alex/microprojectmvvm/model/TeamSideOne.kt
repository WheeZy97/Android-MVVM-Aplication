package com.alex.microprojectmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TeamSideOne (
    @field:PrimaryKey
    @ColumnInfo(name = "team_side_one_name")
    var name: String = "",
    @ColumnInfo(name = "team_side_one_url")
    var url: String = ""
)