package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.MovieDatabase
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.data.MovieRepositoryImpl
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
        return MovieRepositoryImpl(db.dao)
    }
}