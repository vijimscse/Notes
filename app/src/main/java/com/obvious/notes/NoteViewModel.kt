package com.obvious.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.obvious.notes.db.Note
import com.obvious.notes.db.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: NoteRepository
    // LiveData gives us updated notes when they change.
    val allNotes: LiveData<List<Note>>

    init {
        // Gets reference to NoteDao from NoteRoomDatabase to construct
        // the correct NoteRepository.
        val noteDao = NoteDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.getAllNotes()

        viewModelScope.launch {  }
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }
}