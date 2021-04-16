package com.example.movieapp.repository

import com.example.movieapp.application.MovieType
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
): MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList {
        dataSourceRemote.getUpcomingMovies().results.forEach{dataSourceLocal.saveMovie(it.toMovieEntity(MovieType.UPCOMING))}
        return dataSourceLocal.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        dataSourceRemote.getTopRatedMovies().results.forEach{dataSourceLocal.saveMovie(it.toMovieEntity(MovieType.TOP_RATED))}
        return dataSourceLocal.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getTopPopularMovies().results.forEach{dataSourceLocal.saveMovie(it.toMovieEntity(MovieType.POPULAR))}
        return dataSourceLocal.getTopPopularMovies()
    }
}