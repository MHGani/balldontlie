package com.example.balldontlie.network


import com.example.balldontlie.database.DatabasePlayers
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPlayersContainer(val data: List<ValueItem>)
//data class DataItem(
//
////	@Json(name="type")
////	val type: String? = null,
//
//	@Json(name="value") var value: List<ValueItem?>?
//)

data class ValueItem(

	@Json(name="id") val id: Int,

	@Json(name="first_name") val first_name: String,

	@Json(name="last_name") val last_name: String,

	@Json(name="position") val position: String

	)

fun NetworkPlayersContainer.asDatabaseModel() : List<DatabasePlayers> {
	return data.map {
		DatabasePlayers(
			id = it.id,
			first_name = it.first_name,
			last_name = it.last_name,
			position = it.position
		)
	}
}





