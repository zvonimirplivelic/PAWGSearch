package com.zvonimirplivelic.pawgsearch.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zvonimirplivelic.pawgsearch.remote.model.genre.Genre

@Dao
interface PAWGSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres( genres: List<DBGenre>)

    @Query("SELECT * FROM genre_table ORDER BY name ASC")
    fun getGenres(): LiveData<List<DBGenre>>
}