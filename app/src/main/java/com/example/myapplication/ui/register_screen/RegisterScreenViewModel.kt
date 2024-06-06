package com.example.myapplication.ui.register_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.PostService
import com.example.myapplication.data.remote.dto.ErrorServerResponse
import com.example.myapplication.data.remote.dto.PostRequestRegister
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
import okhttp3.Route
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var name by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private  val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val apiService = PostService.create()

    fun onEvent(event: RegisterScreenEvent){
        when(event) {
            is RegisterScreenEvent.OnRegisterClick -> {
                registerUser(login, name, password)
                sendUiEvent(UiEvent.Navigate("main")) //Добавить условие на удачную регистрацию
            }
            is RegisterScreenEvent.OnLoginChange -> {
                login = event.login
            }
            is RegisterScreenEvent.OnNameChange -> {
                name = event.name
            }
            is RegisterScreenEvent.OnPasswordChange -> {
                password = event.password
            }
            is RegisterScreenEvent.OnLinkClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.LOG_IN_SCREEN))
            }
        }
    }

    fun registerUser(email: String, username: String, password: String) {
        val registerRequest = PostRequestRegister(email = email, username = username, parol_user = password)
        viewModelScope.launch {
            val response = apiService.Post_Register(registerRequest)
            when (response) {
                is PostResponseWrapper -> {
                    response.data?.let { userData ->
                        // Сохранение данных в Room
                        val user = User(
                            name = userData.username,
                            login = userData.token ?: "",
                            avatar = userData.image,
                            password = password,
                            token = userData.token ?: ""
                        )
                        userRepository.insertUser(user)
                        Log.d("RegisterScreenViewModel", "Registration successful: ${userData.username}")
                    }
                }
                is ErrorServerResponse -> {
                    println("Registration failed: ${response.message}")
                    Log.e("RegisterScreenViewModel", "Registration failed: ${response.message}")

                }
                else -> {
                    println("An unexpected error occurred during registration")
                    Log.e("RegisterScreenViewModel", "An unexpected error occurred during registration")
                }
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}