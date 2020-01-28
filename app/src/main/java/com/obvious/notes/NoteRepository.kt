package com.obvious.notes

import androidx.lifecycle.LiveData
import com.obvious.notes.db.Note
import com.obvious.notes.db.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllNotes() : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }
}