package com.obvious.notes

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.ActivityResultMatchers.hasResultCode
import androidx.test.espresso.contrib.ActivityResultMatchers.hasResultData
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewNoteActivityTest {

    private lateinit var editTitleInput: String
    private lateinit var editDescriptionInput: String

    @get:Rule
    var activityRule: ActivityTestRule<NewNoteActivity> =
        ActivityTestRule(NewNoteActivity::class.java)

    @Before
    fun initValidStrings() {
        // Specify a valid string.
        editTitleInput = "Title"
        editDescriptionInput = "Description"
    }

    @Test
    fun buttonClick_NotNullText() {
        // Type text and then press the button.
        onView(withId(R.id.edit_title))
            .perform(typeText(editTitleInput))

        onView(withId(R.id.edit_description))
            .perform(typeText(editDescriptionInput), closeSoftKeyboard())

        onView(withId(R.id.button_save)).perform(click())

        assertThat(activityRule.activityResult, hasResultCode(Activity.RESULT_OK))
        assertThat(
            activityRule.activityResult,
            hasResultData(IntentMatchers.hasExtraWithKey(NewNoteActivity.EXTRA_TITLE))
        )
        assertThat(
            activityRule.activityResult,
            hasResultData(IntentMatchers.hasExtraWithKey(NewNoteActivity.EXTRA_DESCRIPTION))
        )
    }
}