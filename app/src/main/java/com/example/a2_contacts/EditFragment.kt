package com.example.a2_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2_contacts.databinding.FragmentEditBinding


class EditFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var contactID: String
    var _binding: FragmentEditBinding? = null
    val binding get() = _binding!!

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
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewID.text = "Edit for $contactID"
    }

    companion object {
        const val CONTACT_ID = "contactID"
    }
}