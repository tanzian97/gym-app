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
    private val trainingMax = dao.getLatestTrainingMax()

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

        val max: Float? = 100f

        if (max != null) {
            _setList.add(WorkoutSet(WorkoutSetType.WARM_UP, 1, roundDownToNearestIncrement(0.4f * max), 5))
            _setList.add(WorkoutSet(WorkoutSetType.WARM_UP, 2, roundDownToNearestIncrement(0.5f * max), 5))
            _setList.add(WorkoutSet(WorkoutSetType.WARM_UP, 3, roundDownToNearestIncrement(0.6f * max), 5))
            _setList.addAll(getMainWorkoutSets(weekCount, max))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 7, roundDownToNearestIncrement(0.5f * max), 10))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 8, roundDownToNearestIncrement(0.5f * max), 10))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 9, roundDownToNearestIncrement(0.5f * max), 10))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 10, roundDownToNearestIncrement(0.5f * max), 10))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 11, roundDownToNearestIncrement(0.5f * max), 10))
        }
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
            3 to listOf(5, 3, 1)
        )

        return listOf(
            WorkoutSet(WorkoutSetType.MAIN, 4, roundDownToNearestIncrement(mapWeekToMultiplierSets[weekCount]?.get(0)!! * max), mapWeekToReps[weekCount]?.get(0)!!),
            WorkoutSet(WorkoutSetType.MAIN, 4, roundDownToNearestIncrement(mapWeekToMultiplierSets[weekCount]?.get(1)!! * max), mapWeekToReps[weekCount]?.get(1)!!),
            WorkoutSet(WorkoutSetType.MAIN, 4, roundDownToNearestIncrement(mapWeekToMultiplierSets[weekCount]?.get(2)!! * max), mapWeekToReps[weekCount]?.get(2)!!),
        )
    }

    private fun roundDownToNearestIncrement(weight: Float) : Float {
        return kotlin.math.floor(weight / 2.5).toFloat() * 2.5f
    }
}