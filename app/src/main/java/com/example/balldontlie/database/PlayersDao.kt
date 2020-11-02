package com.example.balldontlie.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlayersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(players : List<DatabasePlayers>)

    @Query("SELECT * FROM DatabasePlayers")
    fun getLocalDBPlayers(): LiveData<List<DatabasePlayers>>
}

@Database(entities = [DatabasePlayers::class], version  = 2, exportSchema = false)
abstract class PlayersDatabase: RoomDatabase(){
    abstract val playerseDoa: PlayersDao
}