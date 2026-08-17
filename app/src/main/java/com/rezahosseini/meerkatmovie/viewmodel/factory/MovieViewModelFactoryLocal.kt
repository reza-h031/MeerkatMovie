package com.rezahosseini.meerkatmovie.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rezahosseini.meerkatmovie.model.local.repository.MovieRepositoryLocal
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModel
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModelLocal

class MovieViewModelFactoryLocal(
    private val repository: MovieRepositoryLocal
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModelLocal::class.java)) {
            return MovieViewModelLocal(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")    }
}