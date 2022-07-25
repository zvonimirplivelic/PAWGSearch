package com.zvonimirplivelic.pawgsearch.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zvonimirplivelic.pawgsearch.domain.PAWGGenre

@Entity(tableName = "genre_table")
data class DBGenre constructor(
    @PrimaryKey
    var id: Int,
    var name: String,
    var slug: String,
    var isSelected: Boolean?
)

fun List<DBGenre>.asDomainModel(): List<PAWGGenre> {
    return map {
        PAWGGenre(
            id = it.id,
            name = it.name,
            slug = it.slug,
            isSelected = it.isSelected
        )
    }
}
