package com.example.gymapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.databinding.ListWorkoutDayBinding

class HomeAdapter(val clickListener: WorkoutDayListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var data = listOf(
        WorkoutDay(WorkoutType.SQUAT, 1),
        WorkoutDay(WorkoutType.BENCH, 1),
        WorkoutDay(WorkoutType.DEADLIFT, 1),
        WorkoutDay(WorkoutType.OHP, 1),
        WorkoutDay(WorkoutType.SQUAT, 2),
        WorkoutDay(WorkoutType.BENCH, 2),
        WorkoutDay(WorkoutType.DEADLIFT, 2),
        WorkoutDay(WorkoutType.OHP, 2),
        WorkoutDay(WorkoutType.SQUAT, 3),
        WorkoutDay(WorkoutType.BENCH, 3),
        WorkoutDay(WorkoutType.DEADLIFT, 3),
        WorkoutDay(WorkoutType.OHP, 3),
        WorkoutDay(WorkoutType.SQUAT, 4),
        WorkoutDay(WorkoutType.BENCH, 4),
        WorkoutDay(WorkoutType.DEADLIFT, 4),
        WorkoutDay(WorkoutType.OHP, 4),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(val binding: ListWorkoutDayBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WorkoutDay, clickListener: WorkoutDayListener) {
            binding.workoutDay = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListWorkoutDayBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class WorkoutDayListener(val clickListener: (workoutDay: WorkoutDay) -> Unit) {
    fun onClick(workoutDay: WorkoutDay) = clickListener(workoutDay)
}