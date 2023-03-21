package com.example.gymapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SetDatabaseDao {

    @Insert
    suspend fun insertSet(set: Set)

    @Upsert
    suspend fun upsertSet(set: Set)

    @Delete
    suspend fun deleteSet(set: Set)

    @Query("SELECT * FROM table_set ORDER BY setId ASC")
    fun getSetOrderedBySetId(setId: Int): LiveData<List<Set>>



}