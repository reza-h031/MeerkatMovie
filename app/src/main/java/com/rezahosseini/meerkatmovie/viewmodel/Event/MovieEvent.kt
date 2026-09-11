package com.rezahosseini.meerkatmovie.viewmodel.Event

interface MovieEvent {
    data class ShowMessage(
        val message: String
    ) : MovieEvent
}