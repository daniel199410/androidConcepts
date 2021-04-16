package com.example.movieapp.data.local

import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {
    suspend fun getUpcomingMovies(): MovieList = movieDao.getAll().filter { it.movieType == "upcoming" }.toMovieList()

    suspend fun getTopRatedMovies(): MovieList = movieDao.getAll().filter { it.movieType == "top_rated" }.toMovieList()

    suspend fun getTopPopularMovies(): MovieList = movieDao.getAll().filter { it.movieType == "popular" }.toMovieList()

    suspend fun saveMovie(movie: MovieEntity) = movieDao.saveMovie(movie)
}