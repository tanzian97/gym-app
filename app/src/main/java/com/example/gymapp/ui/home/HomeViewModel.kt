package com.example.gymapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.ui.workout.WorkoutSet

class HomeViewModel : ViewModel() {

    private val _navigateToWorkout = MutableLiveData<WorkoutType?>()

    val navigateToWorkout: LiveData<WorkoutType?>
        get() = _navigateToWorkout

    fun onWorkoutTypeClicked(workoutType: WorkoutType) {
        _navigateToWorkout.value = workoutType
    }

    fun doneNavigating() {
        _navigateToWorkout.value = null
    }
}