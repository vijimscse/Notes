package com.obvious.notes.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * from note_table ORDER BY title ASC")
    fun getAllNotes() : LiveData<List<Note>>

    @Insert
    suspend fun addNote(note : Note)

}