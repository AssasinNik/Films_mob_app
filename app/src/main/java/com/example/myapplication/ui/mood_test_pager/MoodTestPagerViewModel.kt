package com.example.myapplication.ui.mood_test_pager

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.movie_data.Movie
import com.example.myapplication.data.movie_data.MovieRepository
import com.example.myapplication.data.questions_data.QuestionRepository
import com.example.myapplication.data.remote.PostService
import com.example.myapplication.data.remote.dto.FilmErrorResponse
import com.example.myapplication.data.remote.dto.FilmListResponse
import com.example.myapplication.data.remote.dto.PostRequestMood
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.util.Routes
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoodTestPagerViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
): ViewModel() {

    var token by mutableStateOf("")
        private set

    private val uniqueRandomNumbers = generateUniqueRandomNumbers(24)

    val question1 = questionRepository.getQuestionsById(uniqueRandomNumbers[0])

    val question2 = questionRepository.getQuestionsById(uniqueRandomNumbers[1])

    val question3 = questionRepository.getQuestionsById(uniqueRandomNumbers[2])

    val question4 = questionRepository.getQuestionsById(uniqueRandomNumbers[3])

    val question5 = questionRepository.getQuestionsById(uniqueRandomNumbers[4])

    var answer1 by mutableStateOf("")

    var answer2 by mutableStateOf("")

    var answer3 by mutableStateOf("")

    var answer4 by mutableStateOf("")

    var answer5 by mutableStateOf("")

    val genresRating = mutableMapOf(
        "спорт" to 0,
        "биография" to 0,
        "комедия" to 0,
        "ужасы" to 0,
        "боевик" to 0,
        "драма" to 0,
        "мелодрама" to 0,
        "мультфильм" to 0,
        "фэнтези" to 0,
        "фантастика" to 0,
        "криминал" to 0,
        "триллер" to 0,
        "приключения" to 0,
        "детектив" to 0,
        "военный" to 0
    )


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MoodTestPagerEvent) {
        when (event) {
            is MoodTestPagerEvent.onCloseIconClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is MoodTestPagerEvent.onLastPageClick -> {
                val answers = mutableListOf<String>()
                listOf(answer1, answer2, answer3, answer4, answer5).forEach { answer ->
                    answers .addAll(answer.split(" ", ignoreCase = true, limit = 0))
                }

                for (answer in answers) {
                    when (answer.toLowerCase()) {
                        "спорт" -> genresRating["спорт"] = genresRating["спорт"]?.plus(1) ?: 1
                        "биография" -> genresRating["биография"] = genresRating["биография"]?.plus(1) ?: 1
                        "комедия" -> genresRating["комедия"] = genresRating["комедия"]?.plus(1) ?: 1
                        "ужасы" -> genresRating["ужасы"] = genresRating["ужасы"]?.plus(1) ?: 1
                        "боевик" -> genresRating["боевик"] = genresRating["боевик"]?.plus(1) ?: 1
                        "драма" -> genresRating["драма"] = genresRating["драма"]?.plus(1) ?: 1
                        "мелодрама" -> genresRating["мелодрама"] = genresRating["мелодрама"]?.plus(1) ?: 1
                        "мультфильм" -> genresRating["мультфильм"] = genresRating["мультфильм"]?.plus(1) ?: 1
                        "фэнтези" -> genresRating["фэнтези"] = genresRating["фэнтези"]?.plus(1) ?: 1
                        "фантастика" -> genresRating["фантастика"] = genresRating["фантастика"]?.plus(1) ?: 1
                        "криминал" -> genresRating["криминал"] = genresRating["криминал"]?.plus(1) ?: 1
                        "триллер" -> genresRating["триллер"] = genresRating["триллер"]?.plus(1) ?: 1
                        "приключения" -> genresRating["приключения"] = genresRating["приключения"]?.plus(1) ?: 1
                        "детектив" -> genresRating["детектив"] = genresRating["детектив"]?.plus(1) ?: 1
                        "военный" -> genresRating["военный"] = genresRating["военный"]?.plus(1) ?: 1
                    }
                }

                //Твой код получения фильмов и добавление их в ROOM
                getFilms(token,genresRating)
            }
        }
    }
    init {
        viewModelScope.launch {
            userRepository.getUser()?.let { user ->
                token = user.token
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    fun getFilms(token: String , genresRating: MutableMap<String, Int>) {
        val topTwoEntries = genresRating.entries.sortedByDescending { it.value }.take(2)
        val valuesArray = topTwoEntries.map { it.key }.toTypedArray()

        val individualArrays = mutableListOf<Array<String>>()

        valuesArray.forEach { key ->
            individualArrays.add(arrayOf(key))
        }

        val apiService = PostService.create()
        Log.d("MoodScreenViewModel", "Starting getFilms with token: $token")
        if (apiService == null) {
            Log.e("MoodScreenViewModel", "apiService is null at the time of the call")
        } else {
            Log.d("MoodScreenViewModel", "apiService is initialized")
        }
        val filmRequest = PostRequestMood(token, valuesArray)
        viewModelScope.launch {
            val response = apiService.Post_MovieMood(filmRequest)
            when (response) {
                is FilmListResponse -> {
                    var count = 0
                    response.data?.forEach { film ->
                        if (count < 7) {
                            val movie = Movie(
                                movieId = film.movie_id,
                                title = film.title ?: "",
                                titleEn = film.titleEn ?: "",
                                genres = film.genres.joinToString(", "),
                                posterURL = film.posterURL,
                                rating = film.rating.toInt(),
                                length = film.length,
                                description = film.description ?: "",
                                releaseYear = film.releaseDate.toString(),
                                ageLimit = film.ageLimit.toString()
                            )
                            viewModelScope.launch(Dispatchers.IO) {
                                movieRepository.deleteMovies()
                                movieRepository.insertFilms(movie)
                            }
                            Log.d("RegisterScreenViewModel", "Movie fetched and saved: ${film.title}")
                            sendUiEvent(UiEvent.Navigate(Routes.MOVIE_LIST_SCREEN))
                            count++
                        }
                    }
                }
                is FilmErrorResponse -> {
                    println("Fetching movies failed: ${response.message}")
                    Log.e("MoodScreenViewModel", "Fetching movies failed: ${response.message}")
                }
                else -> {
                    println("An unexpected error occurred during fetching movies")
                    Log.e("MoodScreenViewModel", "An unexpected error occurred during fetching movies")
                }
            }
        }
        val filmRequest1 = PostRequestMood(token, individualArrays[0])
        viewModelScope.launch {
            val response = apiService.Post_MovieMood(filmRequest1)
            when (response) {
                is FilmListResponse -> {
                    var count = 0
                    response.data?.forEach { film ->
                        if (count < 3) {
                            val movie = Movie(
                                movieId = film.movie_id,
                                title = film.title ?: "",
                                titleEn = film.titleEn ?: "",
                                genres = film.genres.joinToString(", "),
                                posterURL = film.posterURL,
                                rating = film.rating.toInt(),
                                length = film.length,
                                description = film.description ?: "",
                                releaseYear = film.releaseDate.toString(),
                                ageLimit = film.ageLimit.toString()
                            )
                            viewModelScope.launch(Dispatchers.IO) {
                                movieRepository.insertFilms(movie)
                            }
                            Log.d("RegisterScreenViewModel", "Movie fetched and saved: ${film.title}")
                            sendUiEvent(UiEvent.Navigate(Routes.MOVIE_LIST_SCREEN))
                            count++
                        }
                    }
                }
                is FilmErrorResponse -> {
                    println("Fetching movies failed: ${response.message}")
                    Log.e("MoodScreenViewModel", "Fetching movies failed: ${response.message}")
                }
                else -> {
                    println("An unexpected error occurred during fetching movies")
                    Log.e("MoodScreenViewModel", "An unexpected error occurred during fetching movies")
                }
            }
        }
        val filmRequest2 = PostRequestMood(token, individualArrays[1])
        viewModelScope.launch {
            val response = apiService.Post_MovieMood(filmRequest2)
            when (response) {
                is FilmListResponse -> {
                    var count = 0
                    response.data?.forEach { film ->
                        if (count < 3) {
                            val movie = Movie(
                                movieId = film.movie_id,
                                title = film.title ?: "",
                                titleEn = film.titleEn ?: "",
                                genres = film.genres.joinToString(", "),
                                posterURL = film.posterURL,
                                rating = film.rating.toInt(),
                                length = film.length,
                                description = film.description ?: "",
                                releaseYear = film.releaseDate.toString(),
                                ageLimit = film.ageLimit.toString()
                            )
                            viewModelScope.launch(Dispatchers.IO) {
                                movieRepository.insertFilms(movie)
                            }
                            Log.d("RegisterScreenViewModel", "Movie fetched and saved: ${film.title}")
                            sendUiEvent(UiEvent.Navigate(Routes.MOVIE_LIST_SCREEN))
                            count++
                        }
                    }
                }
                is FilmErrorResponse -> {
                    println("Fetching movies failed: ${response.message}")
                    Log.e("MoodScreenViewModel", "Fetching movies failed: ${response.message}")
                }
                else -> {
                    println("An unexpected error occurred during fetching movies")
                    Log.e("MoodScreenViewModel", "An unexpected error occurred during fetching movies")
                }
            }
        }

    }
    private fun generateUniqueRandomNumbers(max: Int): List<Int> {
        val numbers = mutableSetOf<Int>()
        while (numbers.size < 6) {
            val randomNumber = (0..max).random()
            numbers.add(randomNumber)
        }
        return numbers.toList()
    }
}