package com.example.contactslistapp

import android.app.Application
import com.example.contactslistapp.data.AppDatabase
import com.example.contactslistapp.data.ContactRepository


class ContactsApp : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ContactRepository(database.contactDao()) }
}