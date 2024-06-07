package com.example.myapplication.ui.main_screen

import com.example.myapplication.data.movie_data.Movie
import com.example.myapplication.data.new_movie_data.NewMovie

sealed class MainScreenEvent {

    data class OnMovieClick(val movie: NewMovie): MainScreenEvent()

    data object OnMovieSelectionButtonClick: MainScreenEvent()

    data object OnAvatarClick: MainScreenEvent()

    data object OnSelectedMoviesClick: MainScreenEvent()

}