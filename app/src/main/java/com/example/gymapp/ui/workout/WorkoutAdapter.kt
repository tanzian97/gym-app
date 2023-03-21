package com.example.gymapp.ui.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R

class WorkoutAdapter(private val data: List<WorkoutSet>): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

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
        private val setCount: TextView = itemView.findViewById(R.id.set_count)
        private val setDetails: TextView = itemView.findViewById(R.id.set_details)

        fun bind(item: WorkoutSet) {
            setCount.text = item.setCount.toString()
            setDetails.text = formatSetDetails(item.weight, item.repCount)
        }

        private fun formatSetDetails(weight: Float, repCount: Int): String {
            return "${weight.format(1)} x $repCount"
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