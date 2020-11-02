package com.example.balldontlie.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.balldontlie.database.DatabasePlayers
import com.example.balldontlie.database.PlayersDatabase
import com.example.balldontlie.database.asDomainModel
import com.example.balldontlie.domain.Players
import com.example.balldontlie.network.Players_APIServices
import com.example.balldontlie.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "PlayersRepository"
class PlayersRepository(private val playersApiservices: Players_APIServices, private val database: PlayersDatabase){
    suspend fun refreshPlayers(){
        // worker thread to perform API request and saving data locally
        withContext(Dispatchers.IO){
            val playersList= playersApiservices.getPlayersList().await()
            database.playerseDoa.insertAll(playersList.asDatabaseModel())
        }
    }

    val results: LiveData<List<Players>> = Transformations.map(database.playerseDoa.getLocalDBPlayers()){
        it.asDomainModel()
    }
}