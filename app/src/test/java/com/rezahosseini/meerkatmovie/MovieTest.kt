package com.rezahosseini.meerkatmovie

import com.rezahosseini.meerkatmovie.factory.MovieFactory
import com.rezahosseini.meerkatmovie.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class MovieTest {
    private lateinit var movie:Movie
    @Before
    fun setup(){
        movie=MovieFactory.create()
    }
    @Test
    fun movie_isNotNullAndOkTitle_byFactory(){
        assertNotNull(movie)
        assertEquals("Pulp Fiction",movie.name)
    }
    @Test
    fun movieDefault_isOkTitle_byFactory(){
        val movie=MovieFactory.createDefault(id = 2,name = "Fight Club",1999)
        assertEquals("Fight Club",movie.name)
        assertEquals(1999,movie.year)
    }
    @Test
    fun movieNull_isNull_byFactory(){
        val movie=MovieFactory.createNullMovie()
        assertNull(movie)
    }
    @Test
    fun movieInvalid_isOkTitle_byFactory(){
        val movie=MovieFactory.createInvalidMovie()
        assertNotEquals("Pulp Fiction",movie.name)
    }
}