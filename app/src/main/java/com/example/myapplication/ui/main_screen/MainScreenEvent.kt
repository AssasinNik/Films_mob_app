package com.example.myapplication.ui.main_screen

import com.example.myapplication.data.movie_data.Movie

sealed class MainScreenEvent {

    data class OnMovieClick(val movie: Movie): MainScreenEvent()

    data object OnMovieSelectionButtonClick: MainScreenEvent()

    data object OnAvatarClick: MainScreenEvent()

    data object OnPopularMoviesClick: MainScreenEvent()

}