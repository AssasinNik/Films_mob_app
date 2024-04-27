package com.example.myapplication.ui.user_screen

import com.example.myapplication.data.user_data.User

sealed class UserScreenEvent {
    data class OnLoginChange(val login: String): UserScreenEvent()
    data class OnPasswordChange(val password: String): UserScreenEvent()
    data class OnNewPasswordChange(val newPassword: String): UserScreenEvent()
    object OnEditUserDataClick: UserScreenEvent()
    object OnSaveChangesClick: UserScreenEvent()
}