package com.example.gymapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.database.TrainingMaxDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsDialogViewModel(
    private val database: TrainingMaxDatabaseDao
): ViewModel() {

    var squatMax: Float = 0f

    var benchMax: Float = 0f

    var deadliftMax: Float = 0f

    var ohpMax: Float = 0f

    fun onSaveMaxes(trainingMax: TrainingMax) {
        squatMax = trainingMax.squatMax
        benchMax = trainingMax.benchMax
        deadliftMax = trainingMax.deadliftMax
        ohpMax = trainingMax.ohpMax

        viewModelScope.launch {
            update(trainingMax)
        }
    }

    fun onAutoIncrementMaxes() {
        val increment = 2.5f

        squatMax += increment
        benchMax += increment
        deadliftMax += increment
        ohpMax += increment

        val trainingMax = TrainingMax(
            squatMax = squatMax,
            benchMax = benchMax,
            deadliftMax = deadliftMax,
            ohpMax = ohpMax
        )

        viewModelScope.launch {
            update(trainingMax)
        }
    }

    private suspend fun update(trainingMax: TrainingMax) {
        withContext(Dispatchers.IO) {
            database.upsertTrainingMax(trainingMax)
        }
    }


}