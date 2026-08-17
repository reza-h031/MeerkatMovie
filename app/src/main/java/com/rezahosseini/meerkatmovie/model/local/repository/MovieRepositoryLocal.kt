package com.rezahosseini.meerkatmovie.model.local.repository

import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.model.local.MovieEntity
import com.rezahosseini.meerkatmovie.model.local.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryLocal@Inject constructor(
    private val movieDao: MovieDao
){
    fun getAllMovie(): Flow<List<Movie>> {
        return movieDao.getAllMovies().map { movieEntitys ->
            movieEntitys.map { movieEntity ->
                movieEntity.toMovie()
            }
        }
    }
    suspend fun insertMovie(movie: Movie){
        movieDao.insertMovie(movie.toEntity())
    }
    suspend fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie.toEntity())
    }
    fun MovieEntity.toMovie(): Movie {
        return Movie(
            id = id,
            name = name,
            year = year
        )
    }
    fun Movie.toEntity(): MovieEntity {
        return MovieEntity(
            id = id,
            name = name,
            year = year
        )
    }
}