package com.example.contactslistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.contactslistapp.ContactsApp
import com.example.contactslistapp.databinding.FragmentContactListBinding
import com.example.contactslistapp.ui.adapter.ContactAdapter
import com.example.contactslistapp.ui.viewmodel.ContactViewModel
import com.example.contactslistapp.ui.viewmodel.ContactViewModelFactory
import com.google.android.material.snackbar.Snackbar

class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactViewModel by activityViewModels {
        ContactViewModelFactory((requireActivity().application as ContactsApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContactAdapter { contact ->
            val action = ContactListFragmentDirections.actionContactListFragmentToContactDetailsFragment(contact)
            findNavController().navigate(action)
        }
        binding.recyclerViewContacts.adapter = adapter

        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            contacts?.let {
                adapter.submitList(it)
                binding.textViewEmptyState.isVisible = it.isEmpty()
            }
        }

        binding.fabAddContact.setOnClickListener {
            findNavController().navigate(
                ContactListFragmentDirections.actionContactListFragmentToAddContactFragment()
            )
        }

        setupSearchView()
        setupSwipeToDelete(adapter)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }

    private fun setupSwipeToDelete(adapter: ContactAdapter) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val contact = adapter.currentList[position]
                viewModel.deleteContact(contact)
                Snackbar.make(binding.root, "Contact deleted", Snackbar.LENGTH_SHORT).show()
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerViewContacts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}