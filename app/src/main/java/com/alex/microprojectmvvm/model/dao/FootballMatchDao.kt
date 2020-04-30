package com.alex.microprojectmvvm.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alex.microprojectmvvm.model.FootballMatch

@Dao
interface FootballMatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatches(matches: List<FootballMatch>)

    @Query("SELECT * FROM FootballMatch")
    fun getMatchesList() : LiveData<List<FootballMatch>>

    @Query("SELECT * FROM FootballMatch WHERE title IN (:title)")
    fun getFootballMatchByTitle(title: String) : LiveData<FootballMatch>

    @Insert
    fun insert(footballMatch: FootballMatch)

    @Update
    fun update(footballMatch: FootballMatch)

    @Delete
    fun delete(footballMatch: FootballMatch)
}