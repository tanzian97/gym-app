package com.example.gymapp.ui.workout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.database.TrainingMaxDatabaseDao
import com.example.gymapp.ui.home.WorkoutType
import kotlinx.coroutines.*

class WorkoutViewModel(
    val database: TrainingMaxDatabaseDao,
    workoutType: WorkoutType,
    weekCount: Int
) : ViewModel() {

    private var viewmodelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    // TODO if training max has not been set, should get user to set it
    private val trainingMax = MutableLiveData<TrainingMax>()

    private var max = MutableLiveData<Float>()

    private val _setList = mutableListOf<WorkoutSet>()

    val setList: List<WorkoutSet>
        get() = _setList

    init {
        // Issue is that trainingMax is null.
        initialiseLatestTrainingMaxes()

        max.value = getMaxForType(workoutType)

        val maximum = max.value ?: 0f

        _setList.addAll(getWarmUpWorkoutSets(maximum))
        _setList.addAll(getMainWorkoutSets(weekCount, maximum))
        _setList.addAll(getBBBWorkoutSets(maximum))
    }

    private fun initialiseLatestTrainingMaxes() {
        uiScope.launch {
            trainingMax.value = getLatestTrainingMaxesFromDatabase()
        }
    }

    private suspend fun getLatestTrainingMaxesFromDatabase(): TrainingMax? {
        return withContext(Dispatchers.IO) {
            database.getLatestTrainingMax()
        }
    }

    private fun getMaxForType(workoutType: WorkoutType): Float? {
        return when (workoutType) {
            WorkoutType.SQUAT -> trainingMax.value?.squatMax
            WorkoutType.BENCH -> trainingMax.value?.benchMax
            WorkoutType.DEADLIFT -> trainingMax.value?.deadliftMax
            WorkoutType.OHP -> trainingMax.value?.ohpMax
        }
    }

    private fun getWarmUpWorkoutSets(max: Float): List<WorkoutSet> {
        val sets = mutableListOf<WorkoutSet>()
        val warmUpSetMultipliers = listOf(0.4f, 0.5f, 0.6f)

        for (i in 0..2) {
            sets.add(WorkoutSet(WorkoutSetType.WARM_UP, i + 1, warmUpSetMultipliers[i] * max, 5))
        }
        return sets
    }

    private fun getMainWorkoutSets(weekCount: Int, max: Float): List<WorkoutSet> {
        val mapWeekToMultiplierSets = mapOf(
            1 to listOf(0.65f, 0.75f, 0.85f),
            2 to listOf(0.7f, 0.8f, 0.9f),
            3 to listOf(0.75f, 0.85f, 0.95f),
            4 to listOf(0.4f, 0.5f, 0.6f)
        )
        val mapWeekToReps = mapOf(
            1 to listOf(5, 5, 5),
            2 to listOf(3, 3, 3),
            3 to listOf(5, 3, 1),
            4 to listOf(5, 5, 5)
        )

        val sets = mutableListOf<WorkoutSet>()
        val mainSetMultipliers = mapWeekToMultiplierSets[weekCount]!!
        val mainSetReps = mapWeekToReps[weekCount]!!

        for (i in 0..2) {
            sets.add(WorkoutSet(WorkoutSetType.MAIN, i + 4, mainSetMultipliers[i] * max, mainSetReps[i]))
        }
        return sets
    }

    private fun getBBBWorkoutSets(max: Float): List<WorkoutSet> {
        val sets = mutableListOf<WorkoutSet>()
        val bbbSetMultiplier = 0.5f

        for (i in 0..4) {
            sets.add(WorkoutSet(WorkoutSetType.BBB, i + 7, bbbSetMultiplier * max, 10))
        }
        return sets
    }

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }
}