package com.zvonimirplivelic.pawgsearch.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zvonimirplivelic.pawgsearch.model.genre.Genre

interface PAWGSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genre: Genre)

    @Query("SELECT * FROM genre_table ORDER BY name ASC")
    fun getGenres(): LiveData<List<Genre>>
}