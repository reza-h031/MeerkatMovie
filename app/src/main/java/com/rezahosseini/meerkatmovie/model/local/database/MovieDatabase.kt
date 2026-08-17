package com.rezahosseini.meerkatmovie.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rezahosseini.meerkatmovie.model.local.MovieEntity
import com.rezahosseini.meerkatmovie.model.local.dao.MovieDao

@Database(
    entities = [MovieEntity::class],
    version = 1)
abstract class MovieDatabase :RoomDatabase(){
    abstract fun movieDao (): MovieDao
    object MovieDatabaseProvider{
        @Volatile
        private var MOVIEDATABASE:MovieDatabase?=null
        fun getDatabase(context:Context):MovieDatabase{
            return MOVIEDATABASE?: synchronized(this){
                val movieDatabase= Room.databaseBuilder(
                    context = context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                MOVIEDATABASE=movieDatabase
                movieDatabase
            }
        }
    }

}