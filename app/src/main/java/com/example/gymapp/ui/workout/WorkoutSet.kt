package com.example.gymapp.ui.workout

data class WorkoutSet (
    val setType: WorkoutSetType,
    val setCount: Int,
    var weight: Float,
    val repCount: Int,
) {
    init {
        weight = roundDownToNearestIncrement(weight)
    }

    private fun roundDownToNearestIncrement(weight: Float) : Float {
        return kotlin.math.floor(weight / 2.5f) * 2.5f
    }
}
