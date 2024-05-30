package com.example.myapplication.ui.register_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.PostService
import com.example.myapplication.data.remote.dto.ErrorServerResponse
import com.example.myapplication.data.remote.dto.PostRequestRegister
import com.example.myapplication.data.remote.dto.PostResponseWrapper
import com.example.myapplication.data.user_data.User
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.ui.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(

    private val userRepository: UserRepository
) : ViewModel() {
    private val apiService = PostService.create()
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
}