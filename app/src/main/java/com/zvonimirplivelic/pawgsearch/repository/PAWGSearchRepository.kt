package com.zvonimirplivelic.pawgsearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zvonimirplivelic.pawgsearch.db.DBGenre
import com.zvonimirplivelic.pawgsearch.db.PAWGSearchDatabase
import com.zvonimirplivelic.pawgsearch.db.asDomainModel
import com.zvonimirplivelic.pawgsearch.domain.PAWGGenre
import com.zvonimirplivelic.pawgsearch.remote.RetrofitInstance
import com.zvonimirplivelic.pawgsearch.remote.model.genre.asDatabaseModel
import com.zvonimirplivelic.pawgsearch.util.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PAWGSearchRepository(private val database: PAWGSearchDatabase) {

    val genres: LiveData<List<PAWGGenre>> =
        Transformations.map(database.pawgSearchDao.getGenres()) {
            it.asDomainModel()
        }


    suspend fun refreshGenres() {
        withContext(Dispatchers.IO) {
            val genres = RetrofitInstance.api.getRemoteGenreList(API_KEY)
            database.pawgSearchDao.insertGenres(genres.asDatabaseModel())
        }
    }

    suspend fun storeSelectedGenres(genres: List<DBGenre>) {
        withContext(Dispatchers.IO) {
            database.pawgSearchDao.insertGenres(genres)
        }
    }

}
