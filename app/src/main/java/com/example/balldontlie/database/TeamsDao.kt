package com.example.balldontlie.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TeamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teams : List<DatabaseTeams>)

    @Query("SELECT * FROM DatabaseTeams")
    fun getLocalDBTeams(): LiveData<List<DatabaseTeams>>
}

@Database(entities = [DatabaseTeams::class], version  = 2, exportSchema = false)
abstract class TeamsDatabase: RoomDatabase(){
    abstract val teamsDoa: TeamsDao
}