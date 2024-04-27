package com.example.myapplication.ui.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.movie_data.MovieRepository
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.util.Routes
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository
): ViewModel(){

    val movies = movieRepository.getMovies()

    private  val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var login by mutableStateOf("")
        private set

    var avatar by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            userRepository.getUser()?.let { user ->
                login = user.login
                if (user.avatar != null) {
                    avatar = user.avatar
                }
            }
        }
    }

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
            is MainScreenEvent.OnSelectedMoviesClick -> {
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