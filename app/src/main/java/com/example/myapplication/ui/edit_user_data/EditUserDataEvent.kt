package com.example.myapplication.ui.edit_user_data

sealed class EditUserDataEvent {
    data class OnUserNameChange(val name: String): EditUserDataEvent()
    object OnSaveChangesClick: EditUserDataEvent()
    object OnBackIconClick: EditUserDataEvent()
}