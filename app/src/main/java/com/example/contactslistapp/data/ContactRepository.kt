package com.example.contactslistapp.data

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao) {


    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()


    fun searchContacts(query: String): LiveData<List<Contact>> {
        return contactDao.searchContacts(query)
    }


    suspend fun insert(contact: Contact) {
        contactDao.insertContact(contact)
    }


    suspend fun delete(contact: Contact) {
        contactDao.deleteContact(contact)
    }
}