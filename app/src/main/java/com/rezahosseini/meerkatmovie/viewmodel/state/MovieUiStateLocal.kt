package com.rezahosseini.meerkatmovie.viewmodel.state

sealed class MovieUiStateLocal <out T> {
    object loading:MovieUiStateLocal<Nothing>()
    data class Success<T>(val data:T):MovieUiStateLocal<T>()
    data class Error(val message:String):MovieUiStateLocal<Nothing>()
}