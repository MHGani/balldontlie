package com.example.balldontlie.di

import com.example.balldontlie.database.PlayersDatabase
import com.example.balldontlie.database.TeamsDatabase
import com.example.balldontlie.network.Players_APIServices
import com.example.balldontlie.network.Teams_APIServices
import com.example.balldontlie.repository.PlayersRepository
import com.example.balldontlie.repository.TeamsRepository
import org.koin.dsl.module

val repositoryModule= module {
    fun provideRepository(api: Players_APIServices, dao: PlayersDatabase):PlayersRepository{
        return PlayersRepository(api, dao)
    }

    single { provideRepository(get(), get())
    }

    fun provideTeamRepository(api:Teams_APIServices, dao:TeamsDatabase): TeamsRepository{
        return TeamsRepository(api, dao)
    }
    single { provideTeamRepository(get(), get()) }
}

