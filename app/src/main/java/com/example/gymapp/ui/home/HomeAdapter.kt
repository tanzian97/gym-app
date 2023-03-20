package com.example.gymapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.databinding.ListWorkoutDayBinding

class HomeAdapter(val clickListener: WorkoutTypeListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var data = listOf(
        WorkoutDay(WorkoutType.SQUAT),
        WorkoutDay(WorkoutType.BENCH),
        WorkoutDay(WorkoutType.DEADLIFT),
        WorkoutDay(WorkoutType.OHP),
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
        fun bind(item: WorkoutDay, clickListener: WorkoutTypeListener) {
            binding.workoutType = item.workoutType
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

class WorkoutTypeListener(val clickListener: (workoutType: WorkoutType) -> Unit) {
    fun onClick(workoutType: WorkoutType) = clickListener(workoutType)
}