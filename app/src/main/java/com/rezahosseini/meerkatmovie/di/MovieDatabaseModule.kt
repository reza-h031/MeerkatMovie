package com.rezahosseini.meerkatmovie.di

import android.content.Context
import androidx.room.Room
import com.rezahosseini.meerkatmovie.model.local.dao.MovieDao
import com.rezahosseini.meerkatmovie.model.local.database.MovieDatabase
import com.rezahosseini.meerkatmovie.model.local.repository.MovieRepositoryLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }
    @Provides
    fun provideMovieDao(
        database: MovieDatabase
    ): MovieDao {
        return database.movieDao()
    }
}