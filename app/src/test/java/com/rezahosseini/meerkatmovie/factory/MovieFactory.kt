package com.rezahosseini.meerkatmovie.factory

import com.rezahosseini.meerkatmovie.model.Movie

object MovieFactory {
    fun create(): Movie {
        return Movie(0,"Pulp Fiction",1994)
    }
    fun createDefault(
        id:Int=0,
        name:String="Pulp Fiction",
        year:Int=1994
    ):Movie{
        return Movie(id,name,year)
    }
    fun createNullMovie(): Movie?{
        return null
    }
    fun createInvalidMovie(): Movie{
        return Movie(
            -1,"",0
        )
    }

}