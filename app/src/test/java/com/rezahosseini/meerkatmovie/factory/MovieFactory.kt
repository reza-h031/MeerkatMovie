package com.rezahosseini.meerkatmovie.factory

import com.github.javafaker.Faker
import com.rezahosseini.meerkatmovie.model.Movie

object MovieFactory {
    private val faker = Faker()
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
    fun createFakerRandom():Movie{
        return Movie(
            faker.number().numberBetween(0,1000),
            faker.name().fullName(),
            faker.number().numberBetween(1800,2026)
        )
    }
    fun createMovieWithYear(year: Int): Movie {
        return Movie(
            id = faker.number().numberBetween(1, 1000),
            name = faker.name().fullName(),
            year = year
        )
    }


}