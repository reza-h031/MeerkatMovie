package com.rezahosseini.meerkatmovie.repository

import com.rezahosseini.meerkatmovie.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow

class MovieRepository {
    private val _movies = ArrayList<Movie>()
    init {
        startListMovie()
    }
    fun startListMovie(){
        _movies.add(Movie(1, "Inception", 2010))
        _movies.add(Movie(3, "The Dark Knight", 2008))
        _movies.add(Movie(2,"Interstellar", 2014))
    }
    fun addToListMovie(name:String,year:Int){
        val id = (_movies.maxOfOrNull { it.id } ?: 0) + 1
        _movies.add(Movie(id,name,year))
    }
    fun getListMovie(): List<Movie> {
        return _movies.toList()
    }

}