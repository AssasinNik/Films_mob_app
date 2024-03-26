package com.example.myapplication.ui.main_screen

import com.example.myapplication.data.Movie

sealed class MainScreenEvent {

    data class OnMovieClick(val movie: Movie): MainScreenEvent()

    object OnMovieSelectionButtonClick: MainScreenEvent()

    object OnAvatarClick: MainScreenEvent()

    object OnPopularMoviesClick: MainScreenEvent()

}