package com.obvious.notes.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * from note_table ORDER BY Time DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Insert
    suspend fun addNote(note : Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()


    @Query("SELECT * from note_table WHERE title=:title")
    fun getNote(title: String) : Note
}