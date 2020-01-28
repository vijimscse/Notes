package com.obvious.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    private class NoteDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var note = Note("Title1", "Desc1")
                    val noteDao = database.noteDao()
                    noteDao.addNote(note)
                    note = Note("Title2", "Desc2")
                    noteDao.addNote(note)
                    note = Note("Title3", "Desc3")
                    noteDao.addNote(note)
                }
            }
        }

        suspend fun populateDatabase(noteDao: NoteDao) {
            // Delete all content here.
            //noteDao.deleteAll()

            // Add sample words.
            var note = Note("Title1", "Desc1")
            noteDao.addNote(note)
            note = Note("Title2", "Desc2")
            noteDao.addNote(note)
            note = Note("Title3", "Desc3")
            noteDao.addNote(note)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).addCallback(NoteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}