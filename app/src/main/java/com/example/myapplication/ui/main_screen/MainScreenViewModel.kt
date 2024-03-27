package com.example.myapplication.ui.main_screen

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.net.CacheResponse
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    val movies = repository.getMovies()

    private  val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MainScreenEvent){
        when(event) {
            is MainScreenEvent.OnMovieClick -> {

            }
            is MainScreenEvent.OnAvatarClick -> {

            }
            is MainScreenEvent.OnMovieSelectionButtonClick -> {

            }
            is MainScreenEvent.OnPopularMoviesClick -> {

            }
        }
    }
}