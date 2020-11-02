package com.example.balldontlie.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.balldontlie.domain.Players
import kotlinx.android.parcel.Parcelize


@Entity
data class DatabasePlayers(
    @PrimaryKey
    var id : Int,
    var first_name : String,
    var last_name: String,
    var position : String
    )

fun List<DatabasePlayers>.asDomainModel():List<Players> {
    return map {
        Players(
                id = it.id,
                first_name = it.first_name,
                last_name = it.last_name,
                position = it.position
        )
    }
}