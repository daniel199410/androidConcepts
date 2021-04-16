package com.example.movieapp.data.model

import androidx.room.TypeConverter
import com.example.movieapp.application.MovieType

class MovieTypeConverter {
    @TypeConverter
    fun toMovieType(index: Int): MovieType = MovieType.values()[index]

    @TypeConverter
    fun fromMovieType(value: MovieType):Int = value.ordinal
}