package com.zvonimirplivelic.pawgsearch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zvonimirplivelic.pawgsearch.model.genre.GenreResponse
import com.zvonimirplivelic.pawgsearch.util.Constants.DATABASE_NAME


@Database(
    entities = [GenreResponse::class],
    version = 1,
    exportSchema = false
)
abstract class PAWGSearchDatabase : RoomDatabase() {
    abstract fun getPAWGSearchDao(): PAWGSearchDao

    companion object {
        @Volatile
        private var INSTANCE: PAWGSearchDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PAWGSearchDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
    }
}