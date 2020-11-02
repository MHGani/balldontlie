package com.example.balldontlie.di

import android.app.Application
import androidx.room.Room
import com.example.balldontlie.database.PlayersDatabase
import com.example.balldontlie.database.TeamsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

//Module: which return me the instance of the class
val databaseModule = module {

    fun providersDatabase(application: Application):PlayersDatabase{
        return Room.databaseBuilder(application, PlayersDatabase::class.java, "players.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    //singleton: single instance
    single { providersDatabase(androidApplication()) // lifecycle of the application
    }

    fun providersDatabase1(application: Application):TeamsDatabase{
        return Room.databaseBuilder(application, TeamsDatabase::class.java, "teams.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { providersDatabase1(androidApplication()) }
}
