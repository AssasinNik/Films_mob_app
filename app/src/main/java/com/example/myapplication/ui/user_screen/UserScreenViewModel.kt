package com.example.myapplication.ui.user_screen

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.movie_data.Movie
import com.example.myapplication.data.user_data.User
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.ui.Constants
import com.example.myapplication.util.Routes
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.Route
import javax.inject.Inject

@HiltViewModel
class UserScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    var name by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var insertPassword by mutableStateOf("")
        private set

    var avatar by mutableStateOf<Uri>(Constants.DEFAULT_URI)
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
                name = user.name ?: ""
                login = user.login
                password = user.password
                avatar = user.avatar ?: Constants.DEFAULT_URI
                token = user.token
            }
        }
    }

    fun onEvent(event: UserScreenEvent) {
        when (event) {
            is UserScreenEvent.OnEditUserDataClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.EDIT_USER_DATA_SCREEN))
            }
            is UserScreenEvent.OnLoginChange -> {
                login = event.login
            }

            is UserScreenEvent.OnPasswordChange -> {
                insertPassword = event.password
                if (password == insertPassword) {
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

                    if (isPasswordIsValid && newPasswords.isNotBlank()){
                        userRepository.insertUser(
                            User(
                                name = name,
                                login = login,
                                avatar = avatar,
                                password = newPasswords,
                                token = token,
                                userId = 0,
                            )
                        )
                        insertPassword = ""
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

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch{
            _uiEvent.send(event)
        }
    }
}