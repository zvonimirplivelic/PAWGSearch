package com.zvonimirplivelic.rawgsearch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zvonimirplivelic.rawgsearch.util.Constants.DATABASE_NAME


@Database(
    entities = [DBGenre::class, SelectedGenre::class],
    version = 2,
    exportSchema = false
)
abstract class RAWGSearchDatabase : RoomDatabase() {

    abstract val rawgSearchDao: RAWGSearchDao
}

private lateinit var INSTANCE: RAWGSearchDatabase

fun getDatabase(context: Context): RAWGSearchDatabase {
    synchronized(RAWGSearchDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                RAWGSearchDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}