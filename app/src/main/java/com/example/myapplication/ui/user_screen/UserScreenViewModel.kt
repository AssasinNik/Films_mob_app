package com.example.myapplication.ui.user_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.movie_data.Movie
import com.example.myapplication.data.user_data.User
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    var login by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var avatar by mutableStateOf("")
        private set

    var token by mutableStateOf("")
        private set

    var newPasswords by mutableStateOf("")
        private set

    var isPasswordIsValid by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            userRepository.getUser()?.let { user ->
                login = user.login
                password = user.password
                if (user.avatar != null) {
                    avatar = user.avatar
                }
                token = user.token
            }
        }
    }

    fun onEvent(event: UserScreenEvent) {
        when (event) {
            is UserScreenEvent.OnLoginChange -> {
                login = event.login
            }

            is UserScreenEvent.OnPasswordChange -> {
                if (password == event.password) {
                    isPasswordIsValid = true
                }
                else {
                    isPasswordIsValid = false
                }
            }

            is UserScreenEvent.OnNewPasswordChange -> {
                newPasswords = event.newPassword
            }

            is UserScreenEvent.OnSaveChangesClick -> {
                viewModelScope.launch {
                    if (isPasswordIsValid && newPasswords.isNotBlank()){
                        userRepository.insertUser(
                            User(
                                name = "TestName",
                                login = login,
                                avatar = avatar,
                                password = newPasswords,
                                token = token,
                                userId = 0,
                            )
                        )

                        newPasswords = ""
                    }
                    else {
                        if (!isPasswordIsValid){

                        }
                        else if(newPasswords.isBlank()){

                        }
                    }
                }
            }
        }
    }
}