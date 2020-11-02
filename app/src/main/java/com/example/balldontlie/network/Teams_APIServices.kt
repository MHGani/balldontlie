package com.example.balldontlie.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Teams_APIServices {
        @GET(API_Calls.APP_TEAMS_LIST)
        fun getTeamsList(): Deferred<NetworkTeamsContainer>
}