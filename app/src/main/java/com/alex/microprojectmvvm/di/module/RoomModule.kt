package com.alex.microprojectmvvm.di.module

import android.content.Context
import androidx.room.Room
import com.alex.microprojectmvvm.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(val context: Context) {

    @Provides
    @Singleton
    fun provideRoomDatabase() : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "football").fallbackToDestructiveMigration().build()
}