package com.example.balldontlie.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.balldontlie.domain.Players
import com.example.balldontlie.domain.Teams

@Entity
data class DatabaseTeams(
    @PrimaryKey
    //var id : Int,
    var abbreviation : String,
    var city: String,
    var conference : String,
    var division : String,
    var full_name: String,
    var name: String
)

fun List<DatabaseTeams>.asDomainModel():List<Teams> {
    return map {
        Teams(
           // id = it.id,
            abbreviation = it.abbreviation,
            city = it.city,
            conference = it.conference,
            division = it.division,
            full_name = it.full_name,
            name = it.name
        )
    }
}