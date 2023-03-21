package com.example.gymapp.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

interface TrainingMaxDatabaseDao {

    @Insert
    suspend fun insertTrainingMax(trainingMax: TrainingMax)

    @Upsert
    suspend fun upsertTrainingMax(trainingMax: TrainingMax)

    @Delete
    suspend fun deleteTrainingMax(trainingMax: TrainingMax)

    @Query("SELECT * FROM table_training_max ORDER BY id ASC")
    fun getTrainingMaxOrderedById(id: Int): LiveData<List<TrainingMax>>
}