package com.zyp.padc.themovieapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zyp.padc.themovieapp.data.vos.MovieVO
import com.zyp.padc.themovieapp.persistence.daos.MovieDao

@Database(entities = [MovieVO::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val DB_NAME = "THE_MOVIE_DB"

        var dbInstance: MovieDatabase? = null

        fun getDBInstance(context: Context): MovieDatabase? {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return dbInstance
        }
    }

}