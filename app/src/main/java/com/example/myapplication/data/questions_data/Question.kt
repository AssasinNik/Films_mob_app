package com.example.myapplication.data.questions_data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey val questionId: Int? = null,
    val question: String,
    val answer1: String?,
    val answer2: String?,
    val answer3: String?,
    val answer4: String?,
    val genresOfAnswer1: String?,
    val genresOfAnswer2: String?,
    val genresOfAnswer3: String?,
    val genresOfAnswer4: String?,
    val picture: String?,
    val type: Int
)
