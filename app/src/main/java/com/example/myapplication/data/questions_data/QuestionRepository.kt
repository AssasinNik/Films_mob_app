package com.example.myapplication.data.questions_data

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {

    fun getQuestionsById(id: Int): Flow<List<Question>>

    fun getQuestions(): Flow<List<Question>>
}