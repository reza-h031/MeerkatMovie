package com.rezahosseini.meerkatmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezahosseini.meerkatmovie.repository.MovieRepository
import com.rezahosseini.meerkatmovie.viewmodel.state.MovieUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(MovieUiState())

    val uiState: StateFlow<MovieUiState> = _uiState
    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _uiState.value=_uiState.value.copy(
                isLoading = true,
                error = null
            )
            try {
                val movies=movieRepository.getListMovie()
                _uiState.value=_uiState.value.copy(
                    movies,
                    isLoading = false
                )
            }catch (e:Exception){
                _uiState.value=_uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )

            }
        }
    }
    public fun addNewMovies(name:String,year:Int){
        viewModelScope.launch {
            movieRepository.addToListMovie(name,year)
            val movie=movieRepository.getListMovie()
            _uiState.value=_uiState.value.copy(
                movies = movie,
                isLoading = false
            )
        }
    }
}