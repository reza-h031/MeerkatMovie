package com.rezahosseini.meerkatmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezahosseini.meerkatmovie.model.Movie
import com.rezahosseini.meerkatmovie.model.network.repository.MovieProviderImpl
import com.rezahosseini.meerkatmovie.viewmodel.state.MovieUiStateLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieViewModelNetwork@Inject constructor(val movieProviderImpl: MovieProviderImpl) : ViewModel(){
    val uiState:StateFlow<MovieUiStateLocal<List<Movie>>> =
        movieProviderImpl.getAllMovies()
            .map<List<Movie>,MovieUiStateLocal<List<Movie>>>{
            MovieUiStateLocal.Success(it)
        }
            .onStart {
                emit(MovieUiStateLocal.loading)
            }
            .catch {
                emit(MovieUiStateLocal.Error(it.message?:"Unknown error"))
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),MovieUiStateLocal.loading)
}