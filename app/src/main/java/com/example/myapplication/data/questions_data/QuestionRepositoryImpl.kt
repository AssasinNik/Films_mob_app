package com.example.myapplication.data.questions_data

import kotlinx.coroutines.flow.Flow

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao
): QuestionRepository{
    override fun getQuestionsByType(type: Int): Flow<List<Question>> {
        return questionDao.getQuestionsByType(type)
    }

    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestions()
    }
}