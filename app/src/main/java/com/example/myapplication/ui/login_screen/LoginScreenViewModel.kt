package com.example.myapplication.ui.login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.PostService
import com.example.myapplication.data.remote.dto.ErrorServerResponse
import com.example.myapplication.data.remote.dto.PostRequestLogin
import com.example.myapplication.data.remote.dto.PostRequestRegister
import com.example.myapplication.data.remote.dto.PostResponseWrapper
import com.example.myapplication.data.user_data.User
import com.example.myapplication.data.user_data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(

    private val userRepository: UserRepository
) : ViewModel() {
    private val apiService = PostService.create()
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
}