package com.example.myapplication.data.remote

object Routes {

    private const val address = "http://77.221.153.78:8080"
    const val GET_NEW_FILMS = "$address/films/new"
    const val GET_MOVIE_FOR_MOOD = "$address/films/movie_for_mood"
    const val REGISTER = "$address/auth/register"
    const val LOGIN = "$address/auth/login"
    const val AUTH = "$address/auth/authenticate"
    const val CHANGE_IMAGE = "$address/change/image"
    const val CHANGE_PWD = "$address/change/password"
    const val CHANGE_USN = "$address/change/username"
    const val GET_IMAGE = "$address/change/images"
}