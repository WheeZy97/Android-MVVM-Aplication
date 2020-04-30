package com.alex.microprojectmvvm.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.alex.microprojectmvvm.model.TeamSideOne

@Dao
interface TeamSideOneDao {

    @Insert
    fun insert(teamSide: TeamSideOne)

    @Update
    fun update(teamSide: TeamSideOne)

    @Delete
    fun delete(teamSide: TeamSideOne)
}