package com.rezahosseini.meerkatmovie.model.network.repository

import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.model.network.web.MovieService
import com.rezahosseini.meerkatmovie.model.network.web.mapper.MovieWebMapper
import com.rezahosseini.meerkatmovie.model.network.web.model.MovieWeb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieProviderImpl@Inject
constructor(val movieWebMapper: MovieWebMapper,val movieService: MovieService):MovieProvider {

    override fun getAllMovies(): Flow<List<Movie>> {
        return movieService.getAllMovie().map { movieList:List<MovieWeb>->
            movieList.map { movie->
                movieWebMapper.toMovie(movie)
            }
        }
    }
}