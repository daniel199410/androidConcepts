package com.example.movieapp.repository

import com.example.movieapp.application.MovieType
import com.example.movieapp.core.InternetCheck
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
): MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach{dataSourceLocal.saveMovie(it.toMovieEntity(MovieType.UPCOMING))}
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach{dataSourceLocal.saveMovie(it.toMovieEntity(MovieType.TOP_RATED))}
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopPopularMovies().results.forEach{dataSourceLocal.saveMovie(it.toMovieEntity(MovieType.POPULAR))}
            dataSourceLocal.getTopPopularMovies()
        } else {
            dataSourceLocal.getTopPopularMovies()
        }
    }
}