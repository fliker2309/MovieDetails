package com.example.moviedetails.data.db

import android.content.Context
import androidx.room.*
import com.example.moviedetails.data.db.entity.MovieEntity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    //singleton, too bad to create multiply databases,too expensive
    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): MovieDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}