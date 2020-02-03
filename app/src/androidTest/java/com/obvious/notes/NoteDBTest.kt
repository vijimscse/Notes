package com.obvious.notes

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.obvious.notes.db.Note
import com.obvious.notes.db.NoteDao
import com.obvious.notes.db.NoteDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class NoteDBTest {
    private lateinit var noteDao: NoteDao
    private lateinit var db: NoteDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NoteDatabase::class.java
        ).build()
        noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList(): Unit {
        val noteToTest = Note("Hi", "Hello")
        runBlocking {
            noteDao.addNote(noteToTest)
            val note: Note = noteDao.getNote(noteToTest.title)

            Log.d("Title", note.title)
            assertThat(note, equalTo(noteToTest))
        }
    }
}