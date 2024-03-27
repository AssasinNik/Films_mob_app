package com.example.myapplication.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.util.Routes
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
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
                sendUiEvent(UiEvent.Navigate(Routes.MOVIE_SCREEN + "?movieId=${event.movie.movieId}"))
            }
            is MainScreenEvent.OnAvatarClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.USER_SCREEN))
            }
            is MainScreenEvent.OnMovieSelectionButtonClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.PRE_MOVIE_SELECTION_SCREEN))
            }
            is MainScreenEvent.OnPopularMoviesClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.MOVIE_LIST_SCREEN))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}