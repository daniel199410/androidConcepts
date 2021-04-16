package com.example.movieapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.MovieTypeConverter

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(MovieTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "movie_db").build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}