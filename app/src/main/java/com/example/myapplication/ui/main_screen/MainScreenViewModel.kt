package com.example.myapplication.ui.main_screen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.movie_data.MovieRepository
import com.example.myapplication.data.remote.PostService
import com.example.myapplication.data.remote.dto.PostRequestRegister
import com.example.myapplication.data.remote.dto.PostResponseWrapper
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.ui.Constants
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

    var name by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set

    var avatar by mutableStateOf(Constants.DEFAULT_URI.toString())
        private set

    private val service = PostService.create()  // Создание экземпляра сервиса

    init {
        //registerUser("ilya57@gmail.com", "Nikita", "Parol1810!")
        viewModelScope.launch {

            userRepository.getUser()?.let { user ->
                name = user.name ?: ""
                login = user.login
                avatar = user.avatar ?: Constants.DEFAULT_URI.toString()
            }
        }
    }


    /*
        fun registerUser(email: String, username: String, parol: String) {
            val registerRequest = PostRequestRegister(email = email, username = username, parol_user = parol)
            viewModelScope.launch {
                val response = service.Post_Register(registerRequest)
                when (response) {
                    is PostResponseWrapper -> {
                        // Успешный ответ, обновляем UI
                        response.data?.let { userData ->
                            name = userData.username ?: ""
                            login = userData.token ?: ""  // Используем token в качестве логина для простоты
                        }
                        response.data?.image?.let { imageUrl ->
                            avatar = imageUrl
                        } ?: run {
                            userRepository.getUser()?.let { user ->
                                avatar = user.avatar ?: Constants.DEFAULT_URI.toString()
                            }
                        }
                    }
                    is ErrorServerResponse -> {
                        // Выводим сообщение об ошибке
                        println("Registration failed: ${response.message}")
                        // Сброс значений в случае ошибки
                        name = ""
                        login = ""
                        avatar = Constants.DEFAULT_URI.toString()
                    }
                    else -> {
                        // Обработка неизвестной ошибки
                        println("An unexpected error occurred during registration")
                        name = ""
                        login = ""
                        avatar = Constants.DEFAULT_URI.toString()
                    }
                }
            }
        }

     */
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