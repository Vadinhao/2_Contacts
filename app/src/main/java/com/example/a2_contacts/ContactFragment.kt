package com.example.a2_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.a2_contacts.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private lateinit var contactID: String
    private var _binding : FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactID = it.getString(CONTACT_ID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameTxt.text = "ID: $contactID"

        binding.buttonEdit.setOnClickListener {
            val action = ContactFragmentDirections.actionContactFragmentToEditFragment2(contactID = contactID)
            view.findNavController().navigate(action)
        }
    }

    companion object {
        const val CONTACT_ID = "contactID"
    }
}