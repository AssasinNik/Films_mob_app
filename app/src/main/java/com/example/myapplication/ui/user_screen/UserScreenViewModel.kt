package com.example.myapplication.ui.user_screen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.movie_data.Movie
import com.example.myapplication.data.remote.PostService
import com.example.myapplication.data.remote.dto.ErrorServerResponse
import com.example.myapplication.data.remote.dto.PostRequestPassword
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

    var avatar by mutableStateOf(Constants.DEFAULT_URI.toString())
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
                avatar = ("http://77.221.153.78:8080/change/images/" + user.avatar) ?: Constants.DEFAULT_URI.toString()
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
                changePassword(login, password, newPasswords)
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

    fun changePassword(email: String, parol_user: String, new_parol: String?) {
        val apiService = PostService.create()
        val changePwdRequest = PostRequestPassword(email = email, parol_user = parol_user, new_parol = new_parol, token = token)
        viewModelScope.launch {
            val response = apiService.Post_ChPwd(changePwdRequest)
            when (response) {
                is ErrorServerResponse -> {
                    println("Message Change: ${response.message}")
                    Log.e("changePassword", "Message Change: ${response.message}")

                }
                else -> {
                    println("An unexpected error occurred during registration")
                    Log.e("changePassword", "An unexpected error occurred during changing")
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