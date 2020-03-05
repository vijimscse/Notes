package com.obvious.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class NewNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TITLE = "com.obvious.notes.notelist.title"
        const val EXTRA_DESCRIPTION = "com.obvious.notes.notelist.description"
    }

    private lateinit var editTitleView: EditText
    private lateinit var editDescriptionView: EditText
    private lateinit var errorTitleLengthView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        setTitle(R.string.add_a_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        editTitleView = findViewById(R.id.edit_title)
        editDescriptionView = findViewById(R.id.edit_description)
        errorTitleLengthView = findViewById(R.id.characterLengthLimitText)

        editTitleView.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                //Show max limit text
                errorTitleLengthView.visibility = if (s.length >
                    resources.getInteger(R.integer.max_title_limit)) VISIBLE else GONE

            }
        })
        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitleView.text) ||
                TextUtils.isEmpty(editDescriptionView.text)) {
           Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
        }  else {
                val title = editTitleView.text.toString()
                val description = editDescriptionView.text.toString()
                replyIntent.putExtra(EXTRA_TITLE, title)
                replyIntent.putExtra(EXTRA_DESCRIPTION, description)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
