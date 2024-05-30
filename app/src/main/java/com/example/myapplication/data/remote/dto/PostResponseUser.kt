package com.example.myapplication.data.remote.dto
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
sealed class ServerResponse
@Serializable
data class PostResponseWrapper(
    val data: PostResponseUser?,
    val message: String?,
    val statusCode: StatusCode
) : ServerResponse()
@Serializable
data class ErrorServerResponse(
    val message: String?,
    val statusCode: StatusCode
) : ServerResponse()
@Serializable
data class StatusCode(
    val value: Int,
    val description: String
)
@Serializable
data class PostResponseUser(
    val id: Int,
    val username: String?,
    val email: String,
    val parol: String?=null,
    var token: String?=null,
    val image: String?
)