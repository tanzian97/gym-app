package com.example.gymapp.ui.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R

class WorkoutAdapter(
    private val data: List<WorkoutSet>,
    private val weekCount: Int
): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], weekCount)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val setType: TextView = itemView.findViewById(R.id.set_type)
        private val setDetails: TextView = itemView.findViewById(R.id.set_details)

        fun bind(item: WorkoutSet, weekCount: Int) {
            setType.text = item.setType.toString()
            setDetails.text = formatSetDetails(item.weight, item.repCount, item.setCount, weekCount)
        }

        private fun formatSetDetails(weight: Float, repCount: Int, setCount: Int, weekCount: Int): String {
            return if (weekCount == 4) {
                "${weight.format(1)} kg x $repCount"
            } else {
                when (setCount) {
                    6 -> "${weight.format(1)} kg x $repCount+"
                    else -> "${weight.format(1)} kg x $repCount"
                }
            }
        }

        private fun Float.format(digits: Int) = "%.${digits}f".format(this)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_workout_set, parent, false)
                return ViewHolder(view)
            }
        }
    }

}