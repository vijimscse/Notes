package com.obvious.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class NewNoteActivity : AppCompatActivity() {
    private lateinit var editTitleView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
    }
}
