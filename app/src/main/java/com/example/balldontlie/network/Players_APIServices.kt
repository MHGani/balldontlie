package com.example.balldontlie.network

import com.example.balldontlie.database.DatabasePlayers
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Players_APIServices {
    @GET(API_Calls.APP_PLAYERS_LIST)
    fun getPlayersList(): Deferred<NetworkPlayersContainer>

}