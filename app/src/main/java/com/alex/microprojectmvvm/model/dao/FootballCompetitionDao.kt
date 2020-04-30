package com.alex.microprojectmvvm.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.alex.microprojectmvvm.model.FootballCompetition

@Dao
interface FootballCompetitionDao {

    @Insert
    fun insert(footballCompetition: FootballCompetition)

    @Update
    fun update(footballCompetition: FootballCompetition)

    @Delete
    fun delete(footballCompetition: FootballCompetition)
}