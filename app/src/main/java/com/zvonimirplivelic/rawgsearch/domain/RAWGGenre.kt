package com.zvonimirplivelic.rawgsearch.domain

import android.os.Parcelable
import androidx.lifecycle.Transformations.map
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import kotlinx.parcelize.Parcelize

@Parcelize
data class RAWGGenreList(
    var genreList: List<RAWGGenre>
) : Parcelable

@Parcelize
data class RAWGGenre(
    var id: Int,
    var name: String,
    var slug: String,
    var isSelected: Boolean?
) : Parcelable

fun List<RAWGGenre>.asDatabaseModel(): List<DBGenre> {
    return map {
        DBGenre(
            id = it.id,
            name = it.name,
            slug = it.slug,
            isSelected = false
        )
    }
}
