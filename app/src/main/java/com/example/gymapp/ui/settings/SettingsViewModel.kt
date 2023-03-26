package com.example.gymapp.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.database.TrainingMaxDatabaseDao
import kotlinx.coroutines.*

class SettingsViewModel(
    val dao: TrainingMaxDatabaseDao
) : ViewModel() {

    private var viewmodelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    private val latestTrainingMaxes = MutableLiveData<TrainingMax>()

    init {
        initlaliseLateestTrainingMaxes()
    }

    private fun initlaliseLateestTrainingMaxes() {
        uiScope.launch {
            latestTrainingMaxes.value = getLatestTrainingMaxesFromDatabase()
        }
    }

    private suspend fun getLatestTrainingMaxesFromDatabase(): TrainingMax? {
        return withContext(Dispatchers.IO) {
            dao.getLatestTrainingMax()
        }
    }


    val squatMax: Float = latestTrainingMaxes.value?.squatMax ?: 0f
    val benchMax: Float = latestTrainingMaxes.value?.benchMax ?: 0f
    val deadliftMax: Float = latestTrainingMaxes.value?.deadliftMax ?: 0f
    val ohpMax: Float = latestTrainingMaxes.value?.ohpMax ?: 0f


    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }
}