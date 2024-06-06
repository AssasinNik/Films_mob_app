package com.example.myapplication.ui.login_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.PostService
import com.example.myapplication.data.remote.dto.ErrorServerResponse
import com.example.myapplication.data.remote.dto.PostRequestLogin
import com.example.myapplication.data.remote.dto.PostRequestRegister
import com.example.myapplication.data.remote.dto.PostResponseWrapper
import com.example.myapplication.data.user_data.User
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.ui.register_screen.RegisterScreenEvent
import com.example.myapplication.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(

    private val userRepository: UserRepository
) : ViewModel() {

    var login by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private  val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val apiService = PostService.create()

    fun onEvent(event: LoginScreenEvent){
        when(event) {
            is LoginScreenEvent.OnLoginButtonClick -> {
                loginUser(login, password)
                sendUiEvent(UiEvent.Navigate("main")) //Добавить условия на удачную автроризацию
            }
            is LoginScreenEvent.OnLoginChange -> {
                login = event.login
            }
            is LoginScreenEvent.OnPasswordChange -> {
                password = event.password
            }
        }
    }

    fun loginUser(email: String, password: String) {
        val loginRequest = PostRequestLogin(email = email, parol_user = password)
        viewModelScope.launch {
            val response = apiService.Post_Login(loginRequest)
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
                        Log.d("LoginScreenViewModel", "Login successful: ${userData.username}")
                    }
                }
                is ErrorServerResponse -> {
                    println("Login failed: ${response.message}")
                    Log.e("LoginScreenViewModel", "Login failed: ${response.message}")

                }
                else -> {
                    println("An unexpected error occurred during registration")
                    Log.e("LoginScreenViewModel", "An unexpected error occurred during registration")
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