package com.rezahosseini.meerkatmovie.model.network.web

import com.rezahosseini.meerkatmovie.model.network.web.model.MovieWeb
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface MovieService {
    @GET("assets/icons.json")
    fun getAllMovie() : Flow<List<MovieWeb>>
}