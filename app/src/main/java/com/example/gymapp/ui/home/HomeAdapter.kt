package com.example.gymapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R
import com.example.gymapp.databinding.WorkoutDayBinding

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
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
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(val binding: WorkoutDayBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WorkoutDay) {
            binding.exerciseTypeName.text = item.workoutType.toString()

            binding.exerciseTypeImage.setImageResource(
                when (item.workoutType) {
                    WorkoutType.SQUAT -> R.drawable.ic_sleep_0
                    WorkoutType.BENCH -> R.drawable.ic_sleep_1
                    WorkoutType.DEADLIFT -> R.drawable.ic_sleep_2
                    WorkoutType.OHP -> R.drawable.ic_sleep_3
                }
            )
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WorkoutDayBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class WorkoutTypeListener(val clickListener: (workoutType: WorkoutType) -> Unit) {
    fun onClick(workoutType: WorkoutType) = clickListener(workoutType)
}