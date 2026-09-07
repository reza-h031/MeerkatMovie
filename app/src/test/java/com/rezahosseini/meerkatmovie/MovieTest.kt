package com.rezahosseini.meerkatmovie

import com.rezahosseini.meerkatmovie.factory.MovieFactory
import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.model.network.repository.MovieProviderImpl
import com.rezahosseini.meerkatmovie.repository.MovieRepository
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModel
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModelNetwork
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.junit.Assert.assertEquals

import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

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

    @Test
    fun oldMovie_shouldReturnFalse() {

        // Arrange
        val movie = MovieFactory.createMovieWithYear(1800)

        // Act
        val result = isValidMovie(movie)

        // Assert
        assertFalse(result)
    }
    @Test
    fun movieFake_isNotEmptyAndNameIsNotEmptyAndYearIsOkAndIdIsOk_byFakerRandom(){
        val movie=MovieFactory.createFakerRandom()
        assertNotNull(movie)
        assertFalse(movie.name.isEmpty())
        assertTrue(isOkYear(movie.year))
        assertNotNull(movie.id)
    }
    fun isValidMovie(movie: Movie): Boolean {
        return movie.name.isNotBlank() &&
                movie.year >= 1900
    }
    fun isOkYear(year:Int):Boolean{
        return year>1900
    }
    @Test
    fun listMovie_isNotEmpty_byFakerRandom(){
        val listMovie:ArrayList<Movie> = ArrayList()
        repeat(10) {
            val movie = MovieFactory.createFakerRandom()
            assertNotNull(movie)
            listMovie.add(movie)
        }
//        or assertNotEmpty in jUnit 5
        assertTrue(listMovie.isNotEmpty())
    }
    @Test
    fun loadMovies_shouldReturnMovies() {

        // Arrange
        val movies = listOf(
            Movie(1, "Pulp Fiction", 1994),
            Movie(2, "Fight Club", 1999)
        )

        val repository = mock<MovieRepository>()

        `when`(repository.getListMovie())
            .thenReturn(movies)

        val viewModel = MovieViewModel(repository)

        // Act
        val result = viewModel.getMovies()

        // Assert
        assertEquals(movies, result)

        verify(repository).getListMovie()
    }
}