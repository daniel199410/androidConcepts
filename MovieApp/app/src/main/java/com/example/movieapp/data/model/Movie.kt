package com.example.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Long = -1,
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = -1.0,
    @SerializedName("vote_count")
    val voteCount: Long = -1,
    val movieType: String = "",
)

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Long = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String = "",
    @ColumnInfo(name = "original_title")
    val originalTitle: String = "",
    @ColumnInfo(name = "original_language")
    val originalLanguage: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val posterPath: String = "",
    @ColumnInfo(name = "release_date")
    val releaseDate: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val voteCount: Long = -1,
    @ColumnInfo(name = "movie_type")
    val movieType: String = "",
)

fun List<MovieEntity>.toMovieList(): MovieList = MovieList(this.map { it.toMovie() })

fun MovieEntity.toMovie(): Movie = Movie(
    this.id,
    this.adult,
    this.backdropPath,
    this.originalTitle,
    this.originalLanguage,
    this.overview,
    this.popularity,
    this.posterPath,
    this.releaseDate,
    this.title,
    this.video,
    this.voteAverage,
    this.voteCount,
    this.movieType
)

data class MovieList(
    val results: List<Movie> = listOf()
)