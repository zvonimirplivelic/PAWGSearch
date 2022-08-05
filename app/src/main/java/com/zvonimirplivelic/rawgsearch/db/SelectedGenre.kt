package com.zvonimirplivelic.rawgsearch.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import kotlinx.parcelize.Parcelize

@Entity(tableName = "selected_genre_table")
@Parcelize
data class SelectedGenre(
    @PrimaryKey val id: Int,
    val name: String,
    val slug: String,
    var isSelected: Boolean
) : Parcelable

fun List<SelectedGenre>.asDomainModel(): List<RAWGGenre> {
    return map {
        RAWGGenre(
            id = it.id,
            name = it.name,
            slug = it.slug,
            isSelected = false
        )
    }
}