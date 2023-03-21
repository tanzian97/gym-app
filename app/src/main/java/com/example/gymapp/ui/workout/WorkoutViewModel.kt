package com.example.gymapp.ui.workout

import androidx.lifecycle.ViewModel
import com.example.gymapp.database.TrainingMaxDatabaseDao
import com.example.gymapp.ui.home.WorkoutType

class WorkoutViewModel(
    private val dao: TrainingMaxDatabaseDao,
    private val workoutType: WorkoutType,
    private val weekCount: Int
) : ViewModel() {

    // TODO if training max has not been set, should get user to set it
    private val trainingMax = dao.getLatestTrainingMax()


//    private val _setWeights = MutableLiveData<SetWeights>()
//    val setWeights: LiveData<SetWeights>
//        get() = _setWeights

    // Should I use Transformations.map



    init {
        val max: Float? = when (workoutType) {
            WorkoutType.SQUAT -> trainingMax.value?.squatMax
            WorkoutType.BENCH -> trainingMax.value?.benchMax
            WorkoutType.DEADLIFT -> trainingMax.value?.deadliftMax
            WorkoutType.OHP -> trainingMax.value?.ohpMax
        }

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

//        if (max != null) {
//            setWeights.warmUpSet1 = 0.4f * max
//            setWeights.warmUpSet2 = 0.5f * max
//            setWeights.warmUpSet3 = 0.6f * max
//            // TODO fix !!
//            setWeights.mainSet1 = mapWeekToMultiplierSet1[weekCount]!! * max
//            setWeights.mainSet2 = mapWeekToMultiplierSet2[weekCount]!! * max
//            setWeights.mainSet3 = mapWeekToMultiplierSet3[weekCount]!! * max
//            setWeights.bbbSet = 0.5f * max
//        }
    }

    data class SetWeights(val weekCount: Int) {

        var warmUpSet1: Float = 0f

        var warmUpSet2: Float = 0f

        var warmUpSet3: Float = 0f

        var mainSet1: Float = 0f

        var mainSet2: Float = 0f

        var mainSet3: Float = 0f

        var bbbSet: Float = 0f
    }
}