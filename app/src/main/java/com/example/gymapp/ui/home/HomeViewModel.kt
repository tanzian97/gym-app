package com.example.gymapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.ui.workout.WorkoutSet

class HomeViewModel : ViewModel() {

    private val _navigateToWorkout = MutableLiveData<WorkoutSet?>()

    val navigateToWorkout: LiveData<WorkoutSet?>
        get() = _navigateToWorkout

    fun doneNavigating() {
        _navigateToWorkout.value = null
    }
}