package com.rezahosseini.meerkatmovie.model.network.web

import retrofit2.Retrofit
import javax.inject.Inject

class WebService {
    var retrofit:Retrofit
    private val movieService:MovieService
    @Inject
    constructor(retrofit: Retrofit){
        this.retrofit=retrofit
        movieService=retrofit.create(MovieService::class.java)
    }
    fun getMovieService():MovieService{
        return movieService
    }

}