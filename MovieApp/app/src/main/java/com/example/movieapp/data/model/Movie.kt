package com.example.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Long = -1,
    val adult: Boolean = false,
    @SerializedName("genre_ids")
    val genreIds: List<Long> = listOf(),
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
)

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Long = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String = "",
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String = "",
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "posterPath")
    val posterPath: String = "",
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double = -1.0,
    @ColumnInfo(name = "voteCount")
    val voteCount: Long = -1,
)

data class MovieList(
    val results: List<Movie> = listOf()
)