package com.example.balldontlie.di

import com.example.balldontlie.network.Players_APIServices
import com.example.balldontlie.network.Teams_APIServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): Players_APIServices {
        return retrofit.create(Players_APIServices::class.java)
    }

    single { provideUserApi(get()) }

    fun provideUserApi1(retrofit: Retrofit):Teams_APIServices{
        return retrofit.create(Teams_APIServices::class.java)
    }
    single { provideUserApi1(get()) }
}


