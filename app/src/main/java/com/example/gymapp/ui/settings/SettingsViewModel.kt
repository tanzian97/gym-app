package com.example.gymapp.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.database.TrainingMaxDatabaseDao
import kotlinx.coroutines.*

class SettingsViewModel(
    val database: TrainingMaxDatabaseDao
) : ViewModel() {

    private var viewmodelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    private val latestTrainingMaxes = MutableLiveData<TrainingMax>()

    init {
        initialiseLatestTrainingMaxes()
    }

    private fun initialiseLatestTrainingMaxes() {
        uiScope.launch {
            latestTrainingMaxes.value = getLatestTrainingMaxesFromDatabase()
        }
    }

    private suspend fun getLatestTrainingMaxesFromDatabase(): TrainingMax? {
        return withContext(Dispatchers.IO) {
            database.getLatestTrainingMax()
        }
    }

    var squatMax: Float = latestTrainingMaxes.value?.squatMax ?: 0f
    var benchMax: Float = latestTrainingMaxes.value?.benchMax ?: 0f
    var deadliftMax: Float = latestTrainingMaxes.value?.deadliftMax ?: 0f
    var ohpMax: Float = latestTrainingMaxes.value?.ohpMax ?: 0f

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


    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }
}