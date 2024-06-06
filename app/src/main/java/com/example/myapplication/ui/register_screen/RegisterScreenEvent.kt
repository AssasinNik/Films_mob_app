package com.example.myapplication.ui.register_screen

import com.example.myapplication.ui.user_screen.UserScreenEvent

sealed class RegisterScreenEvent {
    object OnRegisterClick: RegisterScreenEvent()
    data class OnLoginChange(val login: String): RegisterScreenEvent()
    data class OnPasswordChange(val password: String): RegisterScreenEvent()
    data class OnNameChange(val name: String): RegisterScreenEvent()
}