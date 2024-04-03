package com.example.myapplication.ui.movie_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Movie
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieScreenViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var movie by mutableStateOf<Movie?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var posterUrl by mutableStateOf("")
        private set

    private  val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val movieId = savedStateHandle.get<Int>("movieId")!!
        viewModelScope.launch {
            repository.getMovieById(movieId)?.let { movie->
                title = movie.title
                description = movie.description ?: ""
                posterUrl = movie.posterURL ?: ""
                this@MovieScreenViewModel.movie = movie
            }
        }
    }
}

