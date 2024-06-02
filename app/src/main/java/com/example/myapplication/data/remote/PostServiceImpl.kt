package com.example.myapplication.data.remote

import android.util.Log
import com.example.myapplication.data.remote.dto.ErrorServerResponse
import com.example.myapplication.data.remote.dto.FilmErrorResponse
import com.example.myapplication.data.remote.dto.FilmListResponse
import com.example.myapplication.data.remote.dto.FilmResponse
import com.example.myapplication.data.remote.dto.PostRequestImage
import com.example.myapplication.data.remote.dto.PostRequestLogin
import com.example.myapplication.data.remote.dto.PostRequestMood
import com.example.myapplication.data.remote.dto.PostRequestPassword
import com.example.myapplication.data.remote.dto.PostRequestRegister
import com.example.myapplication.data.remote.dto.PostRequestToken
import com.example.myapplication.data.remote.dto.PostRequestUsername
import com.example.myapplication.data.remote.dto.PostResponseDefault
import com.example.myapplication.data.remote.dto.PostResponseImageName
import com.example.myapplication.data.remote.dto.PostResponseWrapper
import com.example.myapplication.data.remote.dto.ServerResponse
import com.example.myapplication.data.remote.dto.StatusCode
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.SerializationException
import java.io.File

class PostServiceImpl(
    private val client: HttpClient
) : PostService {

    override suspend fun Post_New_Film(postRequestToken: PostRequestToken): FilmResponse {
        return try {
            val response: HttpResponse = client.post {
                url(Routes.GET_NEW_FILMS)
                contentType(ContentType.Application.Json)
                body = postRequestToken
            }
            when {
                response.status == HttpStatusCode.OK -> {
                    response.receive<FilmResponse>()
                }
                else -> {
                    FilmErrorResponse(null, response.content.toString(), StatusCode(response.status.value, response.status.description))
                }
            }
        } catch (e: ClientRequestException) {
            println("Client request error: ${e.response.status.description}")
            FilmErrorResponse(null, "Client request error: ${e.localizedMessage}",
                StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: ServerResponseException) {
            println("Server response error: ${e.response.status.description}")
            FilmErrorResponse(null, "Server response error: ${e.localizedMessage}",
                StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: RedirectResponseException) {
            println("Redirect response error: ${e.response.status.description}")
            FilmErrorResponse(null, "Redirect response error: ${e.localizedMessage}",
                StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
            FilmErrorResponse(null, "Unexpected error: ${e.localizedMessage}",
                StatusCode(HttpStatusCode.InternalServerError.value, HttpStatusCode.InternalServerError.description))
        }
    }


    override suspend fun Post_Register(postRequestData: PostRequestRegister): ServerResponse? {
        return try {
            val response: HttpResponse = client.post {
                url(Routes.REGISTER)
                contentType(ContentType.Application.Json)
                body = postRequestData
            }
            when {
                response.status == HttpStatusCode.OK -> {
                    response.receive<ServerResponse>()
                }
                else -> {
                    ErrorServerResponse("Unknown error", StatusCode(response.status.value, response.status.description))
                }
            }
        } catch (e: ClientRequestException) {
            Log.e("ERROR MESSAGING", "Client request error: ${e.response.status.description}")
            ErrorServerResponse("Client request error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: ServerResponseException) {
            Log.e("ERROR MESSAGING", "Server response error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: RedirectResponseException) {
            Log.e("ERROR MESSAGING", "Redirect response error: ${e.response.status.description}")
            ErrorServerResponse("Redirect response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: Exception) {
            Log.e("ERROR MESSAGING", "ERROR: ${e.message}")
            ErrorServerResponse("Unexpected error: ${e.message}", StatusCode(HttpStatusCode.InternalServerError.value, HttpStatusCode.InternalServerError.description))
        }
    }

    override suspend fun Post_Auth(postRequestToken: PostRequestToken): ServerResponse? {
        return try {
            val response: HttpResponse = client.post {
                url(Routes.AUTH)
                contentType(ContentType.Application.Json)
                body = postRequestToken
            }
            when (response.status) {
                HttpStatusCode.OK -> response.receive<PostResponseWrapper>()  // Предполагаем, что сервер возвращает данные пользователя в обертке
                else -> ErrorServerResponse("Auth failed: ${response.status.description}",
                    StatusCode(response.status.value, response.status.description))
            }
        } catch (e: ClientRequestException) {
            println("Client request error: ${e.response.status.description}")
            ErrorServerResponse("Client request error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: ServerResponseException) {
            println("Server response error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: RedirectResponseException) {
            println("Redirect response error: ${e.response.status.description}")
            ErrorServerResponse("Redirect response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
            ErrorServerResponse("Unexpected error: ${e.message}", StatusCode(HttpStatusCode.InternalServerError.value, HttpStatusCode.InternalServerError.description))
        }
    }

    override suspend fun Post_Login(postRequestData: PostRequestLogin): ServerResponse? {
        return try {
            val response: HttpResponse = client.post {
                url(Routes.LOGIN)
                contentType(ContentType.Application.Json)
                body = postRequestData
            }
            when (response.status) {
                HttpStatusCode.OK -> response.receive<PostResponseWrapper>()  // Предполагаем, что сервер возвращает данные пользователя в обертке
                else -> ErrorServerResponse("Login failed: ${response.status.description}",
                    StatusCode(response.status.value, response.status.description))
            }
        } catch (e: ClientRequestException) {
            println("Client request error: ${e.response.status.description}")
            ErrorServerResponse("Client request error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: ServerResponseException) {
            println("Server response error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: RedirectResponseException) {
            println("Redirect response error: ${e.response.status.description}")
            ErrorServerResponse("Redirect response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
            ErrorServerResponse("Unexpected error: ${e.message}", StatusCode(HttpStatusCode.InternalServerError.value, HttpStatusCode.InternalServerError.description))
        }
    }

    override suspend fun Post_MovieMood(postRequestData: PostRequestMood): FilmResponse {
        return try {
            val response: HttpResponse = client.post {
                url(Routes.GET_MOVIE_FOR_MOOD)
                contentType(ContentType.Application.Json)
                body = postRequestData
            }
            when (response.status) {
                HttpStatusCode.OK -> {
                    try {
                        response.receive<FilmListResponse>().also {
                            // Проверяем, содержит ли ответ данные
                            if (it.data.isNullOrEmpty()) {
                                // Если данных нет, возвращаем FilmErrorResponse с сообщением
                                FilmErrorResponse(null, "No films matched your mood", it.statusCode)
                            } else {
                                it  // Возвращаем успешный список фильмов
                            }
                        }
                    } catch (e: SerializationException) {
                        // В случае ошибки десериализации, возвращаем FilmErrorResponse
                        FilmErrorResponse(null, "Error parsing server response: ${e.localizedMessage}",
                            StatusCode(response.status.value, response.status.description))
                    }
                }
                else -> FilmErrorResponse(null, "Failed to fetch films for your mood: ${response.status.description}",
                    StatusCode(response.status.value, response.status.description))
            }
        } catch (e: ClientRequestException) {
            println("Client request error: ${e.response.status.description}")
            FilmErrorResponse(null, "Client request error: ${e.localizedMessage}",
                StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: ServerResponseException) {
            println("Server response error: ${e.response.status.description}")
            FilmErrorResponse(null, "Server response error: ${e.localizedMessage}",
                StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: RedirectResponseException) {
            println("Redirect response error: ${e.response.status.description}")
            FilmErrorResponse(null, "Redirect response error: ${e.localizedMessage}",
                StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
            FilmErrorResponse(null, "Unexpected error: ${e.localizedMessage}",
                StatusCode(HttpStatusCode.InternalServerError.value, HttpStatusCode.InternalServerError.description))
        }
    }


    override suspend fun Post_ChPwd(postRequestData: PostRequestPassword): ErrorServerResponse? {
        return try {
            val response: HttpResponse = client.post {
                url(Routes.CHANGE_PWD)
                contentType(ContentType.Application.Json)
                body = postRequestData
            }
            // Проверяем, был ли запрос успешным
            if (response.status == HttpStatusCode.OK) {
                response.receive<ErrorServerResponse>().also {
                    // Логируем ответ сервера
                    if (it.message!!.isNotEmpty()) {
                        println("Response message: ${it.message}")
                    }
                    return it // Возвращаем объект PostResponseDefault
                }
            } else {
                // В случае ошибки формируем PostResponseDefault с сообщением об ошибке
                println("Failed to change password: ${response.status.description}")
                ErrorServerResponse("Server response error: ${response.status.description}", StatusCode(response.status.value, response.status.description))
            }
        } catch (e: ClientRequestException) {
            println("Client request error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: ServerResponseException) {
            println("Server response error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: RedirectResponseException) {
            println("Redirect response error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(120,
                e.message.toString()
            ))
        }
    }

    override suspend fun Post_ChUsr(postRequestUsername: PostRequestUsername): ErrorServerResponse? {
        return try {
            val response: HttpResponse = client.post {
                url(Routes.CHANGE_PWD)
                contentType(ContentType.Application.Json)
                body = postRequestUsername
            }
            // Проверяем, был ли запрос успешным
            if (response.status == HttpStatusCode.OK) {
                response.receive<ErrorServerResponse>().also {
                    // Логируем ответ сервера
                    if (it.message!!.isNotEmpty()) {
                        println("Response message: ${it.message}")
                    }
                    return it // Возвращаем объект PostResponseDefault
                }
            } else {
                // В случае ошибки формируем PostResponseDefault с сообщением об ошибке
                println("Failed to change password: ${response.status.description}")
                ErrorServerResponse("Server response error: ${response.status.description}", StatusCode(response.status.value, response.status.description))
            }
        } catch (e: ClientRequestException) {
            println("Client request error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: ServerResponseException) {
            println("Server response error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: RedirectResponseException) {
            println("Redirect response error: ${e.response.status.description}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
            ErrorServerResponse("Server response error: ${e.message}", StatusCode(120,
                e.message.toString()
            ))
        }
    }
    override suspend fun ChangeImage(postRequestImage: PostRequestImage): PostResponseImageName? {
        val client = HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }

        return try {
            client.post<PostResponseImageName> {  // Уточните тип возвращаемого значения
                url(Routes.CHANGE_IMAGE)  // Проверьте корректность URL
                contentType(ContentType.MultiPart.FormData)
                body = MultiPartFormDataContent(formData {
                    append("token", postRequestImage.token)
                    append("file", File(postRequestImage.filePath).readBytes(), Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=\"${File(postRequestImage.filePath).name}\"")
                        append(HttpHeaders.ContentType, "image/jpeg")
                    })
                })
            }
        } catch (e: ClientRequestException) {
            println("Client request error: ${e.response.status.description}")
            PostResponseImageName(null, "Client request error: ${e.message}", null, StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: ServerResponseException) {
            println("Server response error: ${e.response.status.description}")
            PostResponseImageName(null, "Server response error: ${e.message}", null, StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: RedirectResponseException) {
            println("Redirect response error: ${e.response.status.description}")
            PostResponseImageName(null, "Redirect response error: ${e.message}", null, StatusCode(e.response.status.value, e.response.status.description))
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
            PostResponseImageName(null, "Unexpected error: ${e.message}", null, StatusCode(HttpStatusCode.InternalServerError.value, HttpStatusCode.InternalServerError.description))
        } finally {
            client.close()
        }
    }


}