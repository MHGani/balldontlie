package com.example.balldontlie.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.balldontlie.database.TeamsDatabase
import com.example.balldontlie.database.asDomainModel
import com.example.balldontlie.domain.Teams
import com.example.balldontlie.network.Teams_APIServices
import com.example.balldontlie.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "TeamsRepository"
class TeamsRepository(private val teamsApiservices: Teams_APIServices, private val database: TeamsDatabase){
    suspend fun refreshTeams(){
        // worker thread to perform API request and saving data locally
        withContext(Dispatchers.IO){
            val teamsList= teamsApiservices.getTeamsList().await()
            database.teamsDoa.insertAll(teamsList.asDatabaseModel())
        }
    }

    val results: LiveData<List<Teams>> = Transformations.map(database.teamsDoa.getLocalDBTeams()){
        it.asDomainModel()
    }
}