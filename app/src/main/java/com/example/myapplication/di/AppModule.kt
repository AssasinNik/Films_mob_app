package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.movie_data.MovieDatabase
import com.example.myapplication.data.movie_data.MovieRepository
import com.example.myapplication.data.movie_data.MovieRepositoryImpl
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
        ).createFromAsset("database/movie.db").build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(db: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(db.movieDao)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            "user_db"
        ).createFromAsset("database/user.db").build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }
}