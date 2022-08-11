package com.zvonimirplivelic.rawgsearch.remote.model.genre

import com.squareup.moshi.JsonClass
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre


@JsonClass(generateAdapter = true)
data class GenreResponse(
    val count: Int,
    val next: Any?,
    val previous: Any?,
    val results: List<Genre>
)


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
