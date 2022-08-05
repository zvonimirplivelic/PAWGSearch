package com.zvonimirplivelic.rawgsearch.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RAWGSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres( genres: List<DBGenre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSelectedGenres(genres: List<SelectedGenre>)

    @Query("SELECT * FROM selected_genre_table WHERE isSelected = 1 ORDER BY slug DESC")
    fun getSelectedGenres(): LiveData<List<SelectedGenre>>

    @Query("SELECT * FROM genre_table ORDER BY name ASC")
    fun getGenres(): LiveData<List<DBGenre>>
}