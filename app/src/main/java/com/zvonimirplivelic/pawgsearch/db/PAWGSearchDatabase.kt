package com.zvonimirplivelic.pawgsearch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zvonimirplivelic.pawgsearch.remote.model.genre.Genre
import com.zvonimirplivelic.pawgsearch.util.Constants.DATABASE_NAME


@Database(
    entities = [DBGenre::class],
    version = 1,
    exportSchema = false
)
abstract class PAWGSearchDatabase : RoomDatabase() {

    abstract val pawgSearchDao: PAWGSearchDao
}

private lateinit var INSTANCE: PAWGSearchDatabase

fun getDatabase(context: Context): PAWGSearchDatabase {
    synchronized(PAWGSearchDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PAWGSearchDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
    return INSTANCE
}