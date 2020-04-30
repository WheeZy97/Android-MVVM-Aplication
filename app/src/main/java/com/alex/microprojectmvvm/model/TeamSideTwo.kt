package com.alex.microprojectmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TeamSideTwo (
    @field:PrimaryKey
    @ColumnInfo(name = "team_side_two_name")
    var name: String = "",
    @ColumnInfo(name = "team_side_two_url")
    var url: String = ""
)