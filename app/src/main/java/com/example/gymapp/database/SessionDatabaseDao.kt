package com.example.gymapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SessionDatabaseDao {

    @Insert
    suspend fun insertSession(session: Session)

    @Upsert
    suspend fun upsertSession(session: Session)

    @Delete
    suspend fun deleteSession(session: Session)

    @Query("SELECT * FROM table_set ORDER BY setId ASC")
    fun getSessionOrderedBySessionId(sessionId: Int): LiveData<List<Session>>

}