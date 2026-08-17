package com.rezahosseini.meerkatmovie.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rezahosseini.meerkatmovie.repository.MovieRepository
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModel

class MovieViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")    }
}