package com.zvonimirplivelic.pawgsearch.model.genre

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre_table")
data class Genre(
    @SerializedName("games")
    @Ignore
    @Expose
    val genreGames: List<GenreGame>,
    @Ignore
    @Expose
    val games_count: Int,
    @Expose
    @PrimaryKey
    val id: Int,
    @Ignore
    @Expose
    val image_background: String,
    @Expose
    val name: String,
    @Expose
    val slug: String,
    val isSelected: Boolean

)