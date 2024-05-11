package com.example.myapplication.ui.mood_test_pager

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.movie_data.MovieRepository
import com.example.myapplication.data.questions_data.QuestionRepository
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoodTestPagerViewModel @Inject constructor(
    private val questionRepository: QuestionRepository
): ViewModel() {

    val question1 = questionRepository.getQuestionsByType(1)

    val question2 = questionRepository.getQuestionsByType(2)

    val question3 = questionRepository.getQuestionsByType(3)

    val question4 = questionRepository.getQuestionsByType(4)

    val question5 = questionRepository.getQuestionsByType(5)


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MoodTestPagerEvent) {
        when (event) {
            is MoodTestPagerEvent.onCloseIconClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}