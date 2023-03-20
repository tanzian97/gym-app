package com.example.gymapp.ui.home

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.gymapp.R

@BindingAdapter("workoutTypeName")
fun TextView.setExerciseTypeName(workoutType: WorkoutType) {
    text = workoutType.toString()
}

@BindingAdapter("weekCount")
fun TextView.setWeekCount(week: Int) {
    val weekText = if (week < 4) {
        "Week $week"
    } else {
        "Deload"
    }
    text = weekText
}

@BindingAdapter("workoutTypeImage")
fun ImageView.setExerciseTypeImage(workoutType: WorkoutType) {
    setImageResource(
        when (workoutType) {
            WorkoutType.SQUAT -> R.drawable.ic_sleep_0
            WorkoutType.BENCH -> R.drawable.ic_sleep_1
            WorkoutType.DEADLIFT -> R.drawable.ic_sleep_2
            WorkoutType.OHP -> R.drawable.ic_sleep_3
        }
    )
}