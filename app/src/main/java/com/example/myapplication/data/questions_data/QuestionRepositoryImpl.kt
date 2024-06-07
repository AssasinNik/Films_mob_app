package com.example.myapplication.data.questions_data

import kotlinx.coroutines.flow.Flow

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao
): QuestionRepository{
    override fun getQuestionsById(id: Int): Flow<List<Question>> {
        return questionDao.getQuestionsById(id)
    }

    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestions()
    }
}