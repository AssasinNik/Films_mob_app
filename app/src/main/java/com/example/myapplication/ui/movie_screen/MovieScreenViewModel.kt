package com.example.myapplication.ui.movie_screen

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.movie_data.Movie
import com.example.myapplication.data.movie_data.MovieRepository
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(), Parcelable {

    var movie by mutableStateOf<Movie?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var posterUrl by mutableStateOf("")
        private set

    var rating by mutableStateOf("")
        private set

    var releaseYear by mutableStateOf("")
        private set

    var ageLimit by mutableStateOf("")
        private set
    var length by mutableStateOf("")
        private set

    private  val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    constructor(parcel: Parcel) : this(
        TODO("repository"),
        TODO("savedStateHandle")
    ) {
    }

    init {
        val movieId = savedStateHandle.get<Int>("movieId")!!
        viewModelScope.launch {
            movieRepository.getMovieById(movieId)?.let { movie->
                title = movie.title ?: ""
                description = movie.description ?: ""
                posterUrl = movie.posterURL
                rating = movie.rating.toString()
                releaseYear = movie.releaseYear
                ageLimit = movie.ageLimit ?: ""
                length = movie.length.toString()
                this@MovieScreenViewModel.movie = movie
            }
        }
    }

    fun onEvent(event: MovieScreenEvent) {
        when(event) {
            is MovieScreenEvent.OnBackIconClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieScreenViewModel> {
        override fun createFromParcel(parcel: Parcel): MovieScreenViewModel {
            return MovieScreenViewModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieScreenViewModel?> {
            return arrayOfNulls(size)
        }
    }
}

