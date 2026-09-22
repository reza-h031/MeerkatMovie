package com.rezahosseini.meerkatmovie.integration

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.model.local.MovieEntity
import com.rezahosseini.meerkatmovie.model.local.dao.MovieDao
import com.rezahosseini.meerkatmovie.model.local.database.MovieDatabase
import com.rezahosseini.meerkatmovie.model.local.repository.MovieRepositoryLocal
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModelLocal
import com.rezahosseini.meerkatmovie.viewmodel.state.MovieUiStateLocal
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import app.cash.turbine.test
import org.junit.After

class MovieTest {
    private lateinit var database: MovieDatabase
    private lateinit var dao: MovieDao
    private lateinit var repository: MovieRepositoryLocal
    @Before
    fun setUp(){
        val context = ApplicationProvider
            .getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            MovieDatabase::class.java
        ).build()

        dao = database.movieDao()

        repository = MovieRepositoryLocal(dao)
    }
    //    test insert dao Integration
    @Test
    fun insertMovie_shouldBeSavedInDatabase() = runTest {
        val dao = database.movieDao()
        val movie = MovieEntity(
            1,
            "Pulp Fiction",
            1994
        )

        dao.insertMovie(movie)

        val result = dao.getAllMovies().first()

        assertEquals(
            listOf(movie),
            result
        )
    }
    //    test insert repository Integration
    @Test
    fun repository_insertMovie_shouldSaveMovie() = runTest {

        val movie = Movie(
            1,
            "Pulp Fiction",
            1994
        )

        repository.insertMovie(movie)

        val result = repository.getAllMovie().first()

        assertEquals(
            listOf(movie),
            result
        )
    }
    //    test insert repository and see in viewModel ui state
    @Test
    fun viewModel_shouldReceiveMoviesFromDatabase() = runTest {

        val movie = Movie(
            1,
            "Pulp Fiction",
            1994
        )

        repository.insertMovie(movie)

        val viewModel = MovieViewModelLocal(repository)

        viewModel.uiState.test {

            assertEquals(
                MovieUiStateLocal.loading,
                awaitItem()
            )

            val state = awaitItem()

            assertEquals(
                MovieUiStateLocal.Success(listOf(movie)),
                state
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
    @After
    fun tearDown() {
        database.close()
    }
}