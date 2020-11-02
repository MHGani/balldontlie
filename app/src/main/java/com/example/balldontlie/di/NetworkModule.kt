package com.example.balldontlie.di


import android.app.Application
import android.util.Log
import com.example.balldontlie.network.API_Calls
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// okhttp, retrofit
val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }
    fun provideCacheTeams(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }


    fun provideHttpClient(cache: Cache): OkHttpClient {
        val logging =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Log.i(
                    "PlayerDetails",
                    "" + message
                )
            }).setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(logging)
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideHttpClientTeams(cache: Cache): OkHttpClient {
        val loggingTeams =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Log.i(
                    "TeamsDetails",
                    "" + message
                )
            }).setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingTeams)
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideTeamsGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_Calls.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    fun provideTeamsRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_Calls.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

// singleton
    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(),get()) }

    
    // TODO: 30/10/2020 Single provides a bean definition. You have provided 2 definitions for each bean in the network module
    // This has caused conflicts and koin doesn't know what you want.
    // Providing 2 definitions in the api module worked fine as the functions are returning different types and therefore different beans
    // single {} aks koin to provide a singleton therefore asking for another in a separate single {} will not work
    
//    single { provideCacheTeams(androidApplication()) }
//    single { provideHttpClientTeams(get()) }
//    single { provideTeamsGson() }
//    single { provideTeamsRetrofit(get(),get()) }
}

