package com.example.gymapp.ui.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.databinding.ListWorkoutSetBinding

class WorkoutAdapter(
    private val data: List<WorkoutSet>,
    private val weekCount: Int,
    private val clickListener: SetCompleteListener
): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], weekCount, clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(val binding: ListWorkoutSetBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WorkoutSet, weekCount: Int, clickListener: SetCompleteListener) {
            binding.workoutSet = item
            binding.weekCount = weekCount
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListWorkoutSetBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SetCompleteListener(val clickListener: (workoutSet: WorkoutSet) -> Unit) {
    fun onClick(workoutSet: WorkoutSet) = clickListener(workoutSet)
}