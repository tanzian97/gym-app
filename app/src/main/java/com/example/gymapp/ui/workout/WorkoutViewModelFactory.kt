package com.example.gymapp.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.database.TrainingMaxDatabaseDao
import com.example.gymapp.ui.home.WorkoutType

class WorkoutViewModelFactory(
    private val dataSource: TrainingMaxDatabaseDao,
    private val workoutType: WorkoutType,
    private val weekCount: Int
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
            return WorkoutViewModel(dataSource, workoutType, weekCount) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}