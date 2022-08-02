package com.zvonimirplivelic.rawgsearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import com.zvonimirplivelic.rawgsearch.db.RAWGSearchDatabase
import com.zvonimirplivelic.rawgsearch.db.asDomainModel
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import com.zvonimirplivelic.rawgsearch.remote.RetrofitInstance
import com.zvonimirplivelic.rawgsearch.remote.model.genre.asDatabaseModel
import com.zvonimirplivelic.rawgsearch.util.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RAWGSearchRepository(private val database: RAWGSearchDatabase) {

    val genres: LiveData<List<RAWGGenre>> =
        Transformations.map(database.RAWGSearchDao.getGenres()) {
            it.asDomainModel()
        }


    suspend fun refreshGenres() {
        if(genres.value?.isEmpty() == true){
            withContext(Dispatchers.IO) {
                val genres = RetrofitInstance.api.getRemoteGenreList(API_KEY)
                database.RAWGSearchDao.insertGenres(genres.asDatabaseModel())
            }
        }
    }

    suspend fun storeSelectedGenres(genres: List<DBGenre>) {
        withContext(Dispatchers.IO) {
            database.RAWGSearchDao.updateGenres(genres)
        }
    }

}
