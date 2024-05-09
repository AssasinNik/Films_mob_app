package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.PostRequest
import com.example.myapplication.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.features.get
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostServiceImpl(
    private val client: HttpClient
) : PostService {
    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try{
            client.post<PostResponse> {
            url(Routes.GET_NEW_FILM)
            contentType(ContentType.Application.Json)
            body = postRequest
        }
        } catch (e: ClientRequestException){
            println("ERROR: ${e.response.status.description}")
            null
        }catch (e: ServerResponseException){
            println("ERROR: ${e.response.status.description}")
            null
        }catch (e: RedirectResponseException){
            println("ERROR: ${e.response.status.description}")
            null
        }
        catch (e: Exception){
            println("ERROR: ${e.message}")
            null
        }
    }
}