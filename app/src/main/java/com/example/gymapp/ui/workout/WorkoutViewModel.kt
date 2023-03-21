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

        val mapWeekToMultiplierSet1 = mapOf(
            1 to 0.65f,
            2 to 0.7f,
            3 to 0.75f,
            4 to 0.4f
        )

        val mapWeekToMultiplierSet2 = mapOf(
            1 to 0.75f,
            2 to 0.8f,
            3 to 0.85f,
            4 to 0.5f
        )

        val mapWeekToMultiplierSet3 = mapOf(
            1 to 0.85f,
            2 to 0.9f,
            3 to 0.95f,
            4 to 0.6f
        )

        if (max != null) {
            _setList.add(WorkoutSet(WorkoutSetType.WARM_UP, 1, 0.4f * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.WARM_UP, 2, 0.5f * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.WARM_UP, 3, 0.6f * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.MAIN, 4, mapWeekToMultiplierSet1[weekCount]!! * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.MAIN, 5, mapWeekToMultiplierSet2[weekCount]!! * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.MAIN, 6, mapWeekToMultiplierSet3[weekCount]!! * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 7, 0.5f * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 8, 0.5f * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 9, 0.5f * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 10, 0.5f * max, 5))
            _setList.add(WorkoutSet(WorkoutSetType.BBB, 11, 0.5f * max, 5))
        }
    }
}