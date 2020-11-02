package com.example.balldontlie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balldontlie.database.DatabasePlayers
import com.example.balldontlie.repository.PlayersRepository
import kotlinx.coroutines.*
import java.lang.Exception

private const val TAG = "PlayersListViewModel"
class PlayersListViewModel (private val playersRepository: PlayersRepository): ViewModel(){
    private val viewModelJob = SupervisorJob()
    private val viewModelScope  = CoroutineScope(viewModelJob + Dispatchers.Main)


    val playersListResults = playersRepository.results

    init {
        refreshFromRepository()
    }

    fun refreshFromRepository(){
        viewModelScope.launch {
            try {
                playersRepository.refreshPlayers()
            }
            catch(networkError: Exception){
                Log.i(TAG, "refreshFromRepository: ${networkError}")
            }
        }
    }

    private val _navigateToSelectedProperty = MutableLiveData<DatabasePlayers>()
    val navigateToSelectedProperty: LiveData<DatabasePlayers>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(playerProperty: DatabasePlayers) {
        _navigateToSelectedProperty.value = playerProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}