package com.example.myapplication.data.questions_data

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {

    fun getQuestionsByType(type: Int): Flow<List<Question>>

    fun getQuestions(): Flow<List<Question>>
}