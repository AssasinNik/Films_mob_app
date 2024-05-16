package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.PostRequestRegister
import com.example.myapplication.data.remote.dto.PostRequestToken
import com.example.myapplication.data.remote.dto.PostResponseNewFilms
import com.example.myapplication.data.remote.dto.PostResponseUser
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostServiceImpl(
    private val client: HttpClient
) : PostService {
    override suspend fun Post_New_Film (postRequestToken: PostRequestToken): PostResponseNewFilms? {
        return try{
            client.post<PostResponseNewFilms> {
            url(Routes.GET_NEW_FILMS)
            contentType(ContentType.Application.Json)
            body = postRequestToken
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

    override suspend fun Post_Register(postRequestRegister: PostRequestRegister): PostResponseUser? {
        return try{
            client.post<PostResponseUser> {
                url(Routes.REGISTER)
                contentType(ContentType.Application.Json)
                body = postRequestRegister
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