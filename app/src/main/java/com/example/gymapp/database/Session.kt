package com.example.gymapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.gymapp.ui.home.WorkoutType
import java.util.*

@Entity("table_session")
@TypeConverters(Converter::class)
data class Session(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "session_id")
    val sessionId: Int,

    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "workout_type")
    val workoutType: WorkoutType,

    @ColumnInfo(name = "sets")
    val sets: SetList,

    @ColumnInfo(name = "training_max")
    val trainingMax: Float
)

data class SetList (
    val setList: List<Set>
)