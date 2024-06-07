package com.example.myapplication.ui.main_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.new_movie_data.NewMovie
import com.example.myapplication.data.new_movie_data.NewMovieRepository
import com.example.myapplication.data.remote.PostService
import com.example.myapplication.data.remote.dto.ErrorServerResponse
import com.example.myapplication.data.remote.dto.FilmErrorResponse
import com.example.myapplication.data.remote.dto.FilmListResponse
import com.example.myapplication.data.remote.dto.PostRequestToken
import com.example.myapplication.data.remote.dto.PostResponseWrapper
import com.example.myapplication.data.user_data.User
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
    private val newMovieRepository: NewMovieRepository,
    private val userRepository: UserRepository
): ViewModel(){


    val movies = newMovieRepository.getMovies()

    private  val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var name by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set

    var avatar by mutableStateOf(Constants.DEFAULT_URI.toString())
        private set
    var token by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            userRepository.getUser()?.let { user ->
                name = user.name ?: ""
                login = user.login
                token = user.token
                avatar = ("http://77.221.153.78:8080/change/images/" + user.avatar) ?: Constants.DEFAULT_URI.toString()
            }
            getNewFilms(token)
        }
    }
    fun onEvent(event: MainScreenEvent){
        when(event) {
            is MainScreenEvent.OnMovieClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.NEW_MOVIE_SCREEN + "?movieId=${event.movie.movieId}"))
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


    fun getNewFilms(token: String) {
        val apiService = PostService.create()
        Log.d("MainScreenViewModel", "Starting getNewFilms with token: $token")
        if (apiService == null) {
            Log.e("MainScreenViewModel", "apiService is null at the time of the call")
        } else {
            Log.d("MainScreenViewModel", "apiService is initialized")
        }
        val filmRequest = PostRequestToken(token)
        viewModelScope.launch {
            val response = apiService.Post_New_Film(filmRequest)
            when (response) {
                is FilmListResponse -> {
                    response.data?.forEach { film ->
                        val movie = NewMovie(
                            movieId = film.movie_id,
                            title = film.title ?: "",
                            titleEn = film.titleEn ?: "",
                            genres = film.genres.joinToString(", "),  // Преобразование списка жанров в строку
                            posterURL = film.posterURL,
                            rating = film.rating.toInt(),
                            length = film.length,
                            description = film.description ?: "",
                            releaseYear = film.releaseDate.toString(),
                            ageLimit = film.ageLimit.toString()
                        )
                        // Предполагается, что у вас есть метод в userRepository для вставки фильма
                        newMovieRepository.insertFilms(movie)
                        Log.d("RegisterScreenViewModel", "Movie fetched and saved: ${film.title}")
                    }
                }
                is FilmErrorResponse -> {
                    println("Fetching movies failed: ${response.message}")
                    Log.e("RegisterScreenViewModel", "Fetching movies failed: ${response.message}")
                }
                else -> {
                    println("An unexpected error occurred during fetching movies")
                    Log.e("RegisterScreenViewModel", "An unexpected error occurred during fetching movies")
                }
            }
        }
    }
    fun authUser(token: String) : Boolean{
        val apiService = PostService.create()
        val authRequest = PostRequestToken(token)
        var flag: Boolean = false
        viewModelScope.launch {
            val response = apiService.Post_Auth(authRequest)
            when (response) {
                is PostResponseWrapper -> {
                    response.data?.let { userData ->
                        // Сохранение данных в Room
                        val user = User(
                            name = userData.username,
                            login = userData.token ?: "",
                            avatar = userData.image,
                            password = userData.parol.toString(),
                            token = userData.token ?: ""
                        )
                        Log.d("MainScreenViewModel", "Auth successful: ${userData.username}")
                    }
                    flag = true
                }
                is ErrorServerResponse -> {
                    println("Auth failed: ${response.message}")
                    Log.e("MainScreenViewModel", "Auth failed: ${response.message}")
                    flag = false

                }
                else -> {
                    println("An unexpected error occurred during registration")
                    Log.e("MainScreenViewModel", "An unexpected error occurred during auth")
                    flag = false
                }
            }
        }
        return flag
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}