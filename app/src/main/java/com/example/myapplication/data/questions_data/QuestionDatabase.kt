package com.example.myapplication.data.questions_data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Question::class],
    exportSchema = false,
    version = 6
)
abstract class QuestionDatabase: RoomDatabase() {

    abstract val questionDao: QuestionDao
}
