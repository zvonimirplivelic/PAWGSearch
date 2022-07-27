package com.zvonimirplivelic.pawgsearch.domain

import com.zvonimirplivelic.pawgsearch.db.DBGenre
import com.zvonimirplivelic.pawgsearch.remote.model.genre.GenreGame

data class PAWGGenre(
    var id: Int,
    var name: String,
    var slug: String,
    var isSelected: Boolean?
)

fun List<PAWGGenre>.asDatabaseModel(): List<DBGenre> {
    return map {
        DBGenre(
            id = it.id,
            name = it.name,
            slug = it.slug,
            isSelected = it.isSelected
        )
    }
}