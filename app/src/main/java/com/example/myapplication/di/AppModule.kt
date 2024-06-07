package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.movie_data.MovieDatabase
import com.example.myapplication.data.movie_data.MovieRepository
import com.example.myapplication.data.movie_data.MovieRepositoryImpl
import com.example.myapplication.data.new_movie_data.NewMovieDatabase
import com.example.myapplication.data.new_movie_data.NewMovieRepository
import com.example.myapplication.data.new_movie_data.NewMovieRepositoryImpl
import com.example.myapplication.data.questions_data.Question
import com.example.myapplication.data.questions_data.QuestionDatabase
import com.example.myapplication.data.questions_data.QuestionRepository
import com.example.myapplication.data.questions_data.QuestionRepositoryImpl
import com.example.myapplication.data.user_data.UserDatabase
import com.example.myapplication.data.user_data.UserRepository
import com.example.myapplication.data.user_data.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "movie_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(db: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(db.movieDao)
    }

    @Provides
    @Singleton
    fun provideNewMovieDatabase(app: Application): NewMovieDatabase {
        return Room.databaseBuilder(
            app,
            NewMovieDatabase::class.java,
            "new_movie_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewMovieRepository(db: NewMovieDatabase): NewMovieRepository {
        return NewMovieRepositoryImpl(db.newMovieDao)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            "user_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun provideQuestionDatabase(app: Application): QuestionDatabase {
        return Room.databaseBuilder(
            app,
            QuestionDatabase::class.java,
            "question_db"
        ).createFromAsset("database/question.db").build()
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(db: QuestionDatabase): QuestionRepository {
        return QuestionRepositoryImpl(db.questionDao)
    }
}