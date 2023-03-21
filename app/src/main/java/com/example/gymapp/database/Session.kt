package com.example.gymapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gymapp.ui.home.WorkoutType
import java.util.*

@Entity("table_session")
data class Session(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "session_id")
    val sessionId: Int,

    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "workout_type")
    val workoutType: WorkoutType,

    @ColumnInfo(name = "set_ids")
    val setIds: List<Int>,

    @ColumnInfo(name = "training_max")
    val trainingMax: Float
)