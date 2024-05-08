package com.example.myapplication.ui.movie_screen

sealed class MovieScreenEvent {
    object OnBackIconClick: MovieScreenEvent()
}