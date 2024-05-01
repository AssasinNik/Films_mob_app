package com.example.myapplication.ui.edit_user_data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.user_data.User
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.util.Routes
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditUserDataViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    var name by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var avatar by mutableStateOf("")
        private set

    var token by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            userRepository.getUser()?.let {user ->
                name = user.name ?: ""
                login = user.login
                password = user.password
                avatar = user.avatar  ?: ""
                token = user.token
            }
        }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent (event: EditUserDataEvent) {
        when(event) {
            is EditUserDataEvent.OnAvatarClick -> {

            }

            is EditUserDataEvent.OnUserNameChange-> {
                name = event.name
            }

            is EditUserDataEvent.OnSaveChangesClick -> {
                viewModelScope.launch {
                    userRepository.insertUser(
                        User(
                            name = name,
                            login = login,
                            avatar = avatar,
                            password = password,
                            token = token,
                            userId = 0,
                        )
                    )
                }
                sendUiEvent(UiEvent.Navigate(Routes.USER_SCREEN))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}