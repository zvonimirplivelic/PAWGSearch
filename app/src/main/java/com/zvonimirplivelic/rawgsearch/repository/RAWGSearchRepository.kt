package com.zvonimirplivelic.rawgsearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import com.zvonimirplivelic.rawgsearch.db.RAWGSearchDatabase
import com.zvonimirplivelic.rawgsearch.db.SelectedGenre
import com.zvonimirplivelic.rawgsearch.db.asDomainModel
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import com.zvonimirplivelic.rawgsearch.remote.RetrofitInstance
import com.zvonimirplivelic.rawgsearch.remote.model.genre.asDatabaseModel
import com.zvonimirplivelic.rawgsearch.util.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class RAWGSearchRepository(private val database: RAWGSearchDatabase) {

    val genres: LiveData<List<RAWGGenre>> =
        Transformations.map(database.rawgSearchDao.getGenres()) {
            it.asDomainModel()
        }

    val selectedGenres: LiveData<List<RAWGGenre>> =
        Transformations.map(database.rawgSearchDao.getSelectedGenres()) {
            it.asDomainModel()
        }

    suspend fun refreshGenres() {
        if (genres.value.isNullOrEmpty()) {
            withContext(Dispatchers.IO) {
                val genres = RetrofitInstance.api.getRemoteGenreList(API_KEY)
                Timber.d("Genres: $genres")
                database.rawgSearchDao.insertGenres(genres.asDatabaseModel())
            }
        }
    }

    suspend fun storeSelectedGenres(genres: List<SelectedGenre>) {
        withContext(Dispatchers.IO) {
            database.rawgSearchDao.insertSelectedGenres(genres)
        }
    }

    suspend fun getGameList(apiKey: String, queryString: String) =
        RetrofitInstance.api.getGameList(apiKey, queryString)

}
