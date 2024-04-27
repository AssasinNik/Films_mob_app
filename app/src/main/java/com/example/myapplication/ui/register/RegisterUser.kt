package com.example.myapplication.ui.register

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

// Модель для десериализации ответа сервера
@Serializable
data class RegistrationResponse(
    val data: UserData?,
    val message: String?,
    val statusCode: StatusCode
)

@Serializable
data class UserData(
    val id: Int,
    val username: String,
    val email: String,
    val token: String,
    val image: String?
)

@Serializable
data class StatusCode(
    val value: Int,
    val description: String
)

public fun registerUser(username: String, email: String, password: String) {
    val client = HttpClient()
    val json = Json { ignoreUnknownKeys = true }

    runBlocking {
        launch(Dispatchers.IO) {
            try {
                val response = client.post<String> {
                    url("http://127.0.0.1:8080/auth/register")
                    body = """
                        {
                            "username": "$username",
                            "email": "$email",
                            "parol_user": "$password"
                        }
                    """.trimIndent()
                }

                // Десериализация ответа сервера
                val registrationResponse = json.decodeFromString<RegistrationResponse>(response)
                if (registrationResponse.statusCode.value == 200) {
                    // Регистрация прошла успешно
                    val userData = registrationResponse.data
                    userData?.let {
                        println("Регистрация прошла успешно!")
                        println("Пользователь: ${it.username}")
                        println("Email: ${it.email}")
                        println("Токен: ${it.token}")
                    }
                } else {
                    // Регистрация не удалась
                    println("Ошибка регистрации: ${registrationResponse.message}")
                }
            } catch (e: Exception) {
                println("Ошибка: ${e.message}")
            } finally {
                client.close()
            }
        }
    }
}