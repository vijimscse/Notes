package com.obvious.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.obvious.notes.db.Note

class MainActivity : AppCompatActivity(), NoteListFragment.OnFragmentInteractionListener {

    private val newNoteActivityRequestCode = 1
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        if (savedInstanceState == null) {
            val newFragment = NoteListFragment.newInstance()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, newFragment)
            transaction.commit()
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNoteActivityRequestCode &&
            resultCode == Activity.RESULT_OK && data != null) {
            val note = Note(data.getStringExtra(NewNoteActivity.EXTRA_TITLE),
                data.getStringExtra(NewNoteActivity.EXTRA_DESCRIPTION))
            noteViewModel.insert(note)
        }
    }

    override fun onFragmentInteraction(note: Note) {
        val newFragment = NoteDetailFragment.newInstance(note)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
