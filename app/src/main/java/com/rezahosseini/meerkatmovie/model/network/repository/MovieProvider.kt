package com.rezahosseini.meerkatmovie.model.network.repository

import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.model.network.web.model.MovieWeb
import kotlinx.coroutines.flow.Flow

interface MovieProvider {
    fun getAllMovies():Flow<List<Movie>>
}