package com.example.gymapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.collections.Set

@Database(entities = [Set::class], version = 1)
abstract class TrainingMaxDatabase : RoomDatabase() {

    abstract val trainingMaxDatabaseDao: TrainingMaxDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: TrainingMaxDatabase? = null

        fun getInstance(context: Context): TrainingMaxDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrainingMaxDatabase::class.java,
                        "session_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}