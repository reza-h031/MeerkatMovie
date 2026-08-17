package com.rezahosseini.meerkatmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.model.local.repository.MovieRepositoryLocal
import com.rezahosseini.meerkatmovie.viewmodel.state.MovieUiState
import com.rezahosseini.meerkatmovie.viewmodel.state.MovieUiStateLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModelLocal@Inject constructor(
    private val repository: MovieRepositoryLocal
) : ViewModel() {

    val uiState: StateFlow<MovieUiStateLocal<List<Movie>>> =
        repository
            .getAllMovie()
            .map<List<Movie>, MovieUiStateLocal<List<Movie>>> {
                MovieUiStateLocal.Success(it)
            }
            .onStart {
                emit(MovieUiStateLocal.loading)
            }
            .catch {
                emit(
                    MovieUiStateLocal.Error(
                        it.message ?: "Unknown error"
                    )
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MovieUiStateLocal.loading
            )

    fun insert(nameNewMovie:String,year:Int) {

        viewModelScope.launch {
            repository.insertMovie(Movie(0,nameNewMovie,year))
        }
    }

    fun delete(movie: Movie) {

        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }
}