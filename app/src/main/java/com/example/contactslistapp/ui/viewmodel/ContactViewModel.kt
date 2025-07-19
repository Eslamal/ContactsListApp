package com.example.contactslistapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.data.ContactRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContactViewModel(
    private val repository: ContactRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>("")

    val contacts: LiveData<List<Contact>> = _searchQuery.switchMap { query ->
        if (query.isNullOrEmpty()) {
            repository.allContacts
        } else {
            repository.searchContacts(query)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }


    fun addContact(contact: Contact) = viewModelScope.launch(dispatcher) {
        repository.insert(contact)
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch(dispatcher) {
        repository.delete(contact)
    }
}