package com.example.myapplication.data.remote

import android.util.Log
import com.example.myapplication.data.remote.dto.PostRequestImage
import com.example.myapplication.data.remote.dto.PostRequestLogin
import com.example.myapplication.data.remote.dto.PostRequestMood
import com.example.myapplication.data.remote.dto.PostRequestPassword
import com.example.myapplication.data.remote.dto.PostRequestRegister
import com.example.myapplication.data.remote.dto.PostRequestToken
import com.example.myapplication.data.remote.dto.PostResponseDefault
import com.example.myapplication.data.remote.dto.PostResponseFilms
import com.example.myapplication.data.remote.dto.PostResponseImageName
import com.example.myapplication.data.remote.dto.PostResponseUser
import com.example.myapplication.data.remote.dto.PostResponseWrapper
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

class PostServiceImpl(
    private val client: HttpClient
) : PostService {

    override suspend fun Post_New_Film (postRequestToken: PostRequestToken): PostResponseFilms? {
        return try{
            client.post<PostResponseFilms> {
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

    override suspend fun Post_Register(postRequestRegister: PostRequestRegister): PostResponseWrapper? {
        return try{
            client.post<PostResponseWrapper> {
                url(Routes.REGISTER)
                contentType(ContentType.Application.Json)
                body = postRequestRegister
            }
        } catch (e: ClientRequestException){
            Log.e("ERROR MESSAGING", "ERROR: ${e.response.status.description}")
            null
        }catch (e: ServerResponseException){
            Log.e("ERROR MESSAGING", "ERROR: ${e.response.status.description}")
            null
        }catch (e: RedirectResponseException){
            Log.e("ERROR MESSAGING", "ERROR: ${e.response.status.description}")
            null
        }
        catch (e: Exception){
            Log.e("ERROR MESSAGING", "ERROR: ${e.message}")
            null
        }
    }

    override suspend fun Post_Auth(postRequestToken: PostRequestToken): PostResponseUser? {
        return try{
            client.post<PostResponseUser> {
                url(Routes.AUTH)
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

    override suspend fun Post_Login(postRequestData: PostRequestLogin): PostResponseUser? {
        return try{
            client.post<PostResponseUser> {
                url(Routes.LOGIN)
                contentType(ContentType.Application.Json)
                body = postRequestData
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

    override suspend fun Post_MovieMood(postRequestData: PostRequestMood): PostResponseFilms? {
        return try{
            client.post<PostResponseFilms> {
                url(Routes.GET_MOVIE_FOR_MOOD)
                contentType(ContentType.Application.Json)
                body = postRequestData
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

    override suspend fun Post_ChPwd(postRequestData: PostRequestPassword): PostResponseDefault? {
        return try{
            client.post<PostResponseDefault> {
                url(Routes.CHANGE_PWD)
                contentType(ContentType.Application.Json)
                body = postRequestData
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
    override suspend fun ChangeImage(postRequestImage: PostRequestImage): PostResponseImageName? {
        return try {
            client.post<PostResponseImageName> { // Измените тип возвращаемого значения на PostResponseImageName
                url(Routes.CHANGE_IMAGE) // Убедитесь, что путь к URL корректный
                contentType(ContentType.MultiPart.FormData)
                body = MultiPartFormDataContent(formData {
                    append("token", postRequestImage.token) // Используйте данные из postRequestImage
                    append("file", postRequestImage.image, Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=\"${postRequestImage.fileName}\"")
                    })
                })
            }
        } catch (e: ClientRequestException) {
            println("ERROR: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            println("ERROR: ${e.response.status.description}")
            null
        } catch (e: RedirectResponseException) {
            println("ERROR: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("ERROR: ${e.message}")
            null
        }
    }


}