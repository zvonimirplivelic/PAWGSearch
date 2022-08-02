package com.zvonimirplivelic.rawgsearch.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import kotlinx.parcelize.Parcelize

@Entity(tableName = "genre_table")
@Parcelize
data class DBGenre constructor(
    @PrimaryKey
    var id: Int,
    var name: String,
    var slug: String,
    var isSelected: Boolean?
) : Parcelable

@Parcelize
data class GenreList(
    var genreList: List<DBGenre>
) : Parcelable

fun List<DBGenre>.asDomainModel(): List<RAWGGenre> {
    return map {
        RAWGGenre(
            id = it.id,
            name = it.name,
            slug = it.slug,
            isSelected = it.isSelected
        )
    }
}
