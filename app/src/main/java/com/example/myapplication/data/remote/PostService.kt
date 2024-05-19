package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.FilmResponse
import com.example.myapplication.data.remote.dto.PostRequestImage
import com.example.myapplication.data.remote.dto.PostRequestLogin
import com.example.myapplication.data.remote.dto.PostRequestMood
import com.example.myapplication.data.remote.dto.PostRequestPassword
import com.example.myapplication.data.remote.dto.PostRequestRegister
import com.example.myapplication.data.remote.dto.PostRequestToken
import com.example.myapplication.data.remote.dto.PostResponseDefault
import com.example.myapplication.data.remote.dto.PostResponseImageName
import com.example.myapplication.data.remote.dto.ServerResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface PostService {
    suspend fun Post_New_Film(postRequestToken: PostRequestToken): FilmResponse
    suspend fun Post_Register(postRequestData: PostRequestRegister): ServerResponse?
    suspend fun Post_Auth(postRequestToken: PostRequestToken): ServerResponse?
    suspend fun Post_Login(postRequestData: PostRequestLogin): ServerResponse?
    suspend fun Post_ChPwd(postRequestData: PostRequestPassword): PostResponseDefault?
    suspend fun Post_MovieMood(postRequestData: PostRequestMood): FilmResponse
    suspend fun ChangeImage(postRequestImage: PostRequestImage): PostResponseImageName?

    companion object{
        fun create(): PostService{
            return PostServiceImpl(
                client = HttpClient(Android){
                    install(Logging){
                        level = LogLevel.ALL
                    }
                    install(JsonFeature){
                        serializer=KotlinxSerializer()
                    }
                }
            )
        }
    }
}