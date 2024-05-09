package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.PostRequest
import com.example.myapplication.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface PostService {
    suspend fun createPost(postRequest: PostRequest): PostResponse?

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