package com.alex.microprojectmvvm.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.alex.microprojectmvvm.model.TeamSideOne
import com.alex.microprojectmvvm.model.TeamSideTwo

@Dao
interface TeamSideTwoDao {

    @Insert
    fun insert(teamSide: TeamSideTwo)

    @Update
    fun update(teamSide: TeamSideTwo)

    @Delete
    fun delete(teamSide: TeamSideTwo)
}