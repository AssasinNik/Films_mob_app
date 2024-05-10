package com.example.myapplication.data.questions_data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE type = :type")
    fun getQuestionsByType(type: Int): Flow<List<Question>>

    @Query("SELECT * FROM questions")
    fun getQuestions(): Flow<List<Question>>
}