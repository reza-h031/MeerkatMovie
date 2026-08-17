package com.rezahosseini.meerkatmovie.model.network.web.mapper

import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.model.network.web.model.MovieWeb

class MovieWebMapper {
    fun toMovie(movieWeb: MovieWeb):Movie{
        return Movie(movieWeb.id,movieWeb.name,movieWeb.year)
    }
    fun toMovieWeb(movie: Movie):MovieWeb{
        return MovieWeb(movie.id,movie.name,movie.year)
    }
}