package com.zvonimirplivelic.pawgsearch.remote.model.genre

import com.squareup.moshi.JsonClass
import com.zvonimirplivelic.pawgsearch.db.DBGenre
import com.zvonimirplivelic.pawgsearch.domain.PAWGGenre


@JsonClass(generateAdapter = true)
data class GenreResponse(
    val count: Int,
    val next: Any?,
    val previous: Any?,
    val results: List<Genre>
)

/**
 * Convert Network results to database objects
 */
fun GenreResponse.asDomainModel(): List<PAWGGenre> {
    return results.map {
        PAWGGenre(
            id = it.id,
            name = it.name,
            slug = it.slug,
            isSelected = false
        )
    }
}


fun GenreResponse.asDatabaseModel(): List<DBGenre> {
    return results.map {
        DBGenre(
            id = it.id,
            name = it.name,
            slug = it.slug,
            isSelected = false
        )
    }
}
