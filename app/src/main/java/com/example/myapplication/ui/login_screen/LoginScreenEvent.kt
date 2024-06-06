package com.example.myapplication.ui.login_screen

import com.example.myapplication.ui.register_screen.RegisterScreenEvent

sealed class LoginScreenEvent {
    object OnLoginButtonClick: LoginScreenEvent()
    data class OnLoginChange(val login: String): LoginScreenEvent()
    data class OnPasswordChange(val password: String): LoginScreenEvent()

}