package com.example.myapplication.ui.edit_user_data

sealed class EditUserDataEvent {
    data class OnUserNameChange(val name: String): EditUserDataEvent()
    object OnAvatarClick: EditUserDataEvent()
    object OnSaveChangesClick: EditUserDataEvent()
}