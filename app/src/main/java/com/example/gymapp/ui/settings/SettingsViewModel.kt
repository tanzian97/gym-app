package com.example.gymapp.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.database.TrainingMaxDatabaseDao

class SettingsViewModel(
    dao: TrainingMaxDatabaseDao
) : ViewModel() {

    private val latestTrainingMaxes = dao.getLatestTrainingMax()

    val squatMax: Float = latestTrainingMaxes?.squatMax?: 0f
    val benchMax: Float = latestTrainingMaxes?.benchMax?: 0f
    val deadliftMax: Float = latestTrainingMaxes?.deadliftMax?: 0f
    val ohpMax: Float = latestTrainingMaxes?.ohpMax?: 0f

}