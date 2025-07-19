package com.example.contactslistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.contactslistapp.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: ContactDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contact = args.contact
        binding.textViewDetailsName.text = contact.name
        binding.textViewDetailsPhone.text = contact.phone
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}