package com.example.gymapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R

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

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val exerciseTypeName: TextView = itemView.findViewById(R.id.exercise_type_name)
        private val exerciseTypeImage: ImageView = itemView.findViewById(R.id.exercise_type_image)

        fun bind(item: WorkoutDay) {
            exerciseTypeName.text = item.workoutType.toString()

            exerciseTypeImage.setImageResource(
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
                val view = layoutInflater.inflate(R.layout.workout_day, parent, false)
                return ViewHolder(view)
            }
        }
    }
}