package com.zvonimirplivelic.rawgsearch.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RAWGSearchDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGenres( genres: List<DBGenre>)

    @Update
    fun updateGenres(genres: List<DBGenre>)

    @Query("SELECT * FROM genre_table WHERE isSelected = 1 ORDER BY slug DESC")
    fun getSelectedGenres(): LiveData<List<DBGenre>>

    @Query("SELECT * FROM genre_table ORDER BY name ASC")
    fun getGenres(): LiveData<List<DBGenre>>
}