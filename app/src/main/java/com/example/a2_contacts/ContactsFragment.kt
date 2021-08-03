package com.example.a2_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2_contacts.databinding.FragmentContactsBinding


class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var isFav: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = ContactsAdapter(this.context, isFav)

        binding.chip4.setOnClickListener {
            chooseContacts()
        }

        binding.floatingActionButton.setOnClickListener {
            val action = ContactsFragmentDirections.actionContactsFragmentToEditFragment2(contactID = "None")
            view.findNavController().navigate(action)
        }
    }

    private fun chooseContacts() {
        if (isFav) {
            isFav = !isFav
            recyclerView.adapter = ContactsAdapter(this.context, isFav)
        } else {
            isFav = !isFav
            recyclerView.adapter = ContactsAdapter(this.context, isFav)
        }
    }
}