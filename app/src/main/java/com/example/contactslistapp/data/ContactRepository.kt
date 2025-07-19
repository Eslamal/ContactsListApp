package com.example.contactslistapp.data

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao) {

    // LiveData from Room for all contacts
    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    // Function to get LiveData for search results
    fun searchContacts(query: String): LiveData<List<Contact>> {
        return contactDao.searchContacts(query)
    }

    // Suspend function to insert a contact
    suspend fun insert(contact: Contact) {
        contactDao.insertContact(contact)
    }

    // Suspend function to delete a contact
    suspend fun delete(contact: Contact) {
        contactDao.deleteContact(contact)
    }
}