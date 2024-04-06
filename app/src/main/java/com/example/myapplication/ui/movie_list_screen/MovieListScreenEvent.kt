package com.example.myapplication.ui.movie_list_screen

import com.example.myapplication.data.movie_data.Movie

sealed class MovieListScreenEvent {

    data class OnMovieClick(val movie: Movie): MovieListScreenEvent()
}