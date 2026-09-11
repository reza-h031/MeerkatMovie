package com.rezahosseini.meerkatmovie

import com.rezahosseini.meerkatmovie.factory.MovieFactory
import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.repository.MovieRepository
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.`when` as mockWhen
import org.mockito.Mockito.verify
import kotlinx.coroutines.test.runTest
import app.cash.turbine.test
import com.rezahosseini.meerkatmovie.model.local.repository.MovieRepositoryLocal
import com.rezahosseini.meerkatmovie.model.network.repository.MovieProviderImpl
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModelLocal
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModelNetwork
import com.rezahosseini.meerkatmovie.viewmodel.state.MovieUiStateLocal
import kotlinx.coroutines.flow.flow

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

        mockWhen(repository.getListMovie())
            .thenReturn(movies)

        val viewModel = MovieViewModel(repository)

        // Act
        val result = viewModel.getMovies()

        // Assert
        assertEquals(movies, result)

        verify(repository).getListMovie()
    }
    @Test
    fun loadMoviesById_shouldReturnMovies(){
        val movies = listOf(
            Movie(1, "Pulp Fiction", 1994),
            Movie(2, "Fight Club", 1999)
        )
        val repository = mock<MovieRepository>()
        mockWhen(repository.getById(1))
            .thenReturn(movies.get(0))
        val viewModel=MovieViewModel(repository)
        val result=viewModel.getMovieById(1)

        assertEquals(movies.get(0),result)
        verify(repository,times(1)).getById(1)
    }
    @Test
    fun getMovies_returnCoroutine() =runTest  {
        val movies = listOf(
            Movie(1, "Pulp Fiction", 1994),
            Movie(2, "Fight Club", 1999)
        )
        val repository=mock<MovieRepository>()
        mockWhen(repository.getMoviesT())
            .thenReturn(movies)
        val viewModel=MovieViewModel(repository)
        val result=viewModel.getMoviesT()
        assertEquals(movies,result)
        verify(repository, times(1)).getMoviesT()
    }
//    torbin
//    test to test flow by torbin
@Test
fun flow_shouldEmitLoadingThenSuccess() = runTest {

    flow {
        emit("Loading")
        emit("Success")
    }.test {

        assertEquals(
            "Loading",
            awaitItem()
        )

        assertEquals(
            "Success",
            awaitItem()
        )
    }
}
//    test flow error by torbin
    @Test
    fun flow_shouldReturnError() = runTest {

        flow<Int> {
            throw RuntimeException("Network Error")
        }.test {

            val error = awaitError()

            assertEquals(
                "Network Error",
                error.message
            )
        }
    }
//  test success list in state flow Ui state local
    @Test
    fun uiState_shouldEmitLoadingThenSuccess() = runTest {

        // Arrange
        val movies = listOf(
            Movie(1, "Pulp Fiction", 1994),
            Movie(2, "Fight Club", 1999)
        )
        val movies2= listOf(
            Movie(1, "test", 1994),
            Movie(2, "test", 1999)
        )

        val provider = mock<MovieRepositoryLocal>()

        mockWhen(provider.getAllMovie())
            .thenReturn(
                flow {
                    emit(movies)
                    emit(movies2)
                }
            )

        // Act
        val viewModel = MovieViewModelLocal(provider)

        // Assert
        viewModel.uiState.test {

            assertEquals(
                MovieUiStateLocal.loading,
                awaitItem()
            )

            assertEquals(
                MovieUiStateLocal.Success(movies),
                awaitItem()
            )
            assertEquals(
                MovieUiStateLocal.Success(movies2),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
//    test error in state flow Ui state local
    @Test
    fun uiState_shouldEmitLoadingThenError() = runTest {

        // Arrange
        val provider = mock<MovieRepositoryLocal>()

        mockWhen(provider.getAllMovie())
            .thenReturn(
                flow {
                    throw RuntimeException("Network Error")
                }
            )

        // Act
        val viewModel = MovieViewModelLocal(provider)

        // Assert
        viewModel.uiState.test {

            assertEquals(
                MovieUiStateLocal.loading,
                awaitItem()
            )

            assertEquals(
                MovieUiStateLocal.Error("Network Error"),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
//    test empty list in state flow Ui state local
    @Test
    fun uiState_shouldEmitSuccessWithEmptyList() = runTest {

        // Arrange
        val provider = mock<MovieRepositoryLocal>()

        mockWhen(provider.getAllMovie())
            .thenReturn(
                flow {
                    emit(emptyList())
                }
            )

        // Act
        val viewModel = MovieViewModelLocal(provider)

        // Assert
        viewModel.uiState.test {

            assertEquals(
                MovieUiStateLocal.loading,
                awaitItem()
            )

            val state = awaitItem()

            assertTrue(
                state is MovieUiStateLocal.Success &&
                        state.data.isEmpty()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
    @Test
    fun initialState_shouldBeLoading() = runTest {
        val repository = mock<MovieRepositoryLocal>()

        mockWhen(repository.getAllMovie())
            .thenReturn(flow { emit(emptyList()) })

        val viewModel = MovieViewModelLocal(repository)

//        test
        assertEquals(
            MovieUiStateLocal.loading,
            viewModel.uiState.value
        )
//        to
        viewModel.uiState.test{
            assertEquals(
                MovieUiStateLocal.loading,
                awaitItem()
            )

            val state = awaitItem()

            assertTrue(
                state is MovieUiStateLocal.Success &&
                        state.data.isEmpty()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
}