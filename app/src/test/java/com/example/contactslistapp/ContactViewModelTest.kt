package com.example.contactslistapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.data.ContactRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class ContactViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: ContactRepository
    private lateinit var viewModel: ContactViewModel

    @Before
    fun setup() {
        // We no longer need Dispatchers.setMain()

        repository = mock(ContactRepository::class.java)

        // Stub the initial call to prevent crashes
        `when`(repository.allContacts).thenReturn(MutableLiveData(emptyList()))

        // Pass the testDispatcher directly to the ViewModel
        viewModel = ContactViewModel(repository, testDispatcher)
    }

    // We no longer need @After fun tearDown()

    @Test
    fun `deleteContact should call repository delete method`() = runTest {
        // Given
        val contactToDelete = Contact(1, "Eslam", "123456789")

        // When
        viewModel.deleteContact(contactToDelete)

        // Then
        verify(repository).delete(contactToDelete)
    }

    @Test
    fun `addContact should call repository insert method`() = runTest {
        // Given
        val contactToAdd = Contact(name = "New Contact", phone = "987654321")

        // When
        viewModel.addContact(contactToAdd)

        // Then
        verify(repository).insert(contactToAdd)
    }
}