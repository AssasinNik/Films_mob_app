package com.example.myapplication.ui.movie_screen

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieScreenViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

}