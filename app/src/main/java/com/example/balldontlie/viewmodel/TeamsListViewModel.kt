package com.example.balldontlie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balldontlie.database.DatabaseTeams
import com.example.balldontlie.repository.TeamsRepository
import kotlinx.coroutines.*
import java.lang.Exception

private const val TAG = "TeamsListViewModel"
class TeamsListViewModel (private val teamsRepository: TeamsRepository): ViewModel(){
    private val viewModelJob = SupervisorJob()
    private val viewModelScope  = CoroutineScope(viewModelJob + Dispatchers.Main)


    val teamsListResults = teamsRepository.results

    init {
        refreshFromRepository()
    }

    fun refreshFromRepository(){
        viewModelScope.launch {
            try {
                teamsRepository.refreshTeams()
            }
            catch(networkError: Exception){
                Log.i(TAG, "refreshFromRepository: ${networkError}")
            }
        }
    }

    private val _navigateToSelectedProperty = MutableLiveData<DatabaseTeams>()
    val navigateToSelectedProperty: LiveData<DatabaseTeams>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(teamProperty: DatabaseTeams) {
        _navigateToSelectedProperty.value = teamProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}