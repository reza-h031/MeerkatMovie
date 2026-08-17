package com.rezahosseini.meerkatmovie.viewmodel.state

import com.rezahosseini.meerkatmovie.model.Movie

data class MovieUiState (
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)