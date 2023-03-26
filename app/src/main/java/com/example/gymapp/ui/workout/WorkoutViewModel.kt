package com.example.gymapp.ui.workout

import androidx.lifecycle.ViewModel
import com.example.gymapp.database.TrainingMaxDatabaseDao
import com.example.gymapp.ui.home.WorkoutType

class WorkoutViewModel(
    dao: TrainingMaxDatabaseDao,
    workoutType: WorkoutType,
    weekCount: Int
) : ViewModel() {

    // TODO if training max has not been set, should get user to set it
//    private val trainingMax = dao.getLatestTrainingMax()

    private val _setList = mutableListOf<WorkoutSet>()

    val setList: List<WorkoutSet>
        get() = _setList

    init {
//        val max: Float? = when (workoutType) {
//            WorkoutType.SQUAT -> trainingMax.value?.squatMax
//            WorkoutType.BENCH -> trainingMax.value?.benchMax
//            WorkoutType.DEADLIFT -> trainingMax.value?.deadliftMax
//            WorkoutType.OHP -> trainingMax.value?.ohpMax
//        }

        val max: Float? = 97.5f

        if (max != null) {
            _setList.addAll(getWarmUpWorkoutSets(max))
            _setList.addAll(getMainWorkoutSets(weekCount, max))
            _setList.addAll(getBBBWorkoutSets(max))
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
}