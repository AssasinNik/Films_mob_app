package com.example.myapplication.util

sealed class UiEvent {
    data object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
}