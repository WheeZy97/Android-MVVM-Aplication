package com.alex.microprojectmvvm.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alex.microprojectmvvm.model.*
import com.alex.microprojectmvvm.model.converter.DateConverter
import com.alex.microprojectmvvm.model.converter.FootballMatchVideoListConverter
import com.alex.microprojectmvvm.model.dao.*

@Database(entities = [TeamSideOne::class, TeamSideTwo::class,
    FootballMatch::class, FootballCompetition::class], version = 2)
@TypeConverters(DateConverter::class, FootballMatchVideoListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamSideOneDao() : TeamSideOneDao
    abstract fun teamSideTwoDao() : TeamSideTwoDao
    abstract fun footballCompetitionDaoDao() : FootballCompetitionDao
    abstract fun footballMatchDao() : FootballMatchDao
}