package com.example.balldontlie.network

import com.example.balldontlie.database.DatabasePlayers
import com.example.balldontlie.database.DatabaseTeams
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkTeamsContainer(val data: List<ValueItem2>)
//data class DataItem(
//
////	@Json(name="type")
////	val type: String? = null,
//
//	@Json(name="value") var value: List<ValueItem?>?
//)

data class ValueItem2(


    @Json(name="abbreviation") val abbreviation: String,

    @Json(name="city") val city: String,

    @Json(name="conference") val conference: String,

    @Json(name="division") val division: String,

    @Json(name="full_name") val full_name: String,

    @Json(name="name") val name: String
)

fun NetworkTeamsContainer.asDatabaseModel() : List<DatabaseTeams> {
    return data.map {
        DatabaseTeams(
            abbreviation = it.abbreviation,
            city = it.city,
            conference = it.conference,
            division = it.division,
            full_name = it.full_name,
            name = it.name

        )
    }
}
