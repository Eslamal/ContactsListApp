package com.example.contactslistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.contactslistapp.ContactsApp
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.databinding.FragmentAddContactBinding
import com.example.contactslistapp.ui.viewmodel.ContactViewModel
import com.example.contactslistapp.ui.viewmodel.ContactViewModelFactory


class AddContactFragment : Fragment() {

    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactViewModel by activityViewModels {
        ContactViewModelFactory((requireActivity().application as ContactsApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val phone = binding.editTextPhone.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                viewModel.addContact(Contact(name = name, phone = phone))
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}