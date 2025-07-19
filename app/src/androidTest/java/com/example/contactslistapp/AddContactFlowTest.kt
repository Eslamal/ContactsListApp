package com.example.contactslistapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.contactslistapp.data.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf

@RunWith(AndroidJUnit4::class)
class AddContactFlowTest {

    @Before
    fun clearDatabase() {
        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        val db = AppDatabase.getDatabase(context)
        runBlocking {
            db.clearAllTables()
        }
    }

    @Test
    fun addContact_andVerifyItAppearsInList() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.fab_add_contact)).perform(click())

        val newContactName = "Ahmed Ali"
        val newContactPhone = "01012345678"

        onView(withId(R.id.edit_text_name)).perform(typeText(newContactName), closeSoftKeyboard())
        onView(withId(R.id.edit_text_phone)).perform(typeText(newContactPhone), closeSoftKeyboard())

        onView(withId(R.id.button_save)).perform(click())

        onView(withId(R.id.recycler_view_contacts))
            .check(matches(atPosition(0, allOf(
                hasDescendant(withText(newContactName)),
                hasDescendant(withText(newContactPhone))
            ))))
    }
}