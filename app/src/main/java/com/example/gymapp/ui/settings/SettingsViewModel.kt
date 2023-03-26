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

    var squatMax = MutableLiveData<Float>()

    var benchMax = MutableLiveData<Float>()

    var deadliftMax = MutableLiveData<Float>()

    var ohpMax = MutableLiveData<Float>()

    init {
        initialiseLatestTrainingMaxes()
    }

    private fun initialiseLatestTrainingMaxes() {
        uiScope.launch {
            val latestTrainingMax = getLatestTrainingMaxesFromDatabase()
            if (latestTrainingMax != null) {
                squatMax.value = latestTrainingMax.squatMax
                benchMax.value = latestTrainingMax.benchMax
                deadliftMax.value = latestTrainingMax.deadliftMax
                ohpMax.value = latestTrainingMax.ohpMax
            }
        }
    }

    private suspend fun getLatestTrainingMaxesFromDatabase(): TrainingMax? {
        return withContext(Dispatchers.IO) {
            database.getLatestTrainingMax()
        }
    }

    fun onSaveMaxes(trainingMax: TrainingMax) {
        squatMax.value = trainingMax.squatMax
        benchMax.value = trainingMax.benchMax
        deadliftMax.value = trainingMax.deadliftMax
        ohpMax.value = trainingMax.ohpMax

        viewModelScope.launch {
            update(trainingMax)
        }
    }

    fun onAutoIncrementMaxes() {
        val increment = 2.5f

        squatMax.value = squatMax.value?.plus(increment)
        benchMax.value = benchMax.value?.plus(increment)
        deadliftMax.value = deadliftMax.value?.plus(increment)
        ohpMax.value = ohpMax.value?.plus(increment)

        val trainingMax = TrainingMax(
            squatMax = squatMax.value?: 0f,
            benchMax = benchMax.value?: 0f,
            deadliftMax = deadliftMax.value?: 0f,
            ohpMax = ohpMax.value?: 0f
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