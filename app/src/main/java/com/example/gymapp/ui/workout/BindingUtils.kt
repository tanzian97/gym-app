package com.example.gymapp.ui.workout

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("workoutSetType")
fun TextView.setWorkoutSetType(workoutSetType: WorkoutSetType) {
    text = workoutSetType.toString()
}

@BindingAdapter("workoutSetDetails")
fun TextView.setWorkoutSetDetails(workoutSet: WorkoutSet) {
    text = formatSetDetails(workoutSet)
}

private fun formatSetDetails(workoutSet: WorkoutSet): String {
    return if (workoutSet.weekCount != 4 && workoutSet.setCount == 6) {
        "${workoutSet.weight.format(1)} kg x ${workoutSet.repCount}+"
    } else {
        "${workoutSet.weight.format(1)} kg x ${workoutSet.repCount}"
    }
}

private fun Float.format(digits: Int) = "%.${digits}f".format(this)