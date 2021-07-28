package com.example.a2_contacts

import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.a2_contacts.databinding.FragmentContactsBinding


class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        //свят свят свят
        //getContacts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //свят свят свят
        getContacts()
    }

    @SuppressLint("Recycle")
    private fun getContacts(){
        var phoneNumber : String = " "
        var CONTENT_URI : Uri = ContactsContract.Contacts.CONTENT_URI
        var _ID : String = ContactsContract.Contacts._ID
        var DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME
        var HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER
        var IS_FAVORITE = ContactsContract.Contacts.STARRED
        var IMG = ContactsContract.Contacts.PHOTO_URI

        var PhoneCONTENT_URI : Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        var Phone_CONTACT_ID : String = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
        var NUMBER : String = ContactsContract.CommonDataKinds.Phone.NUMBER

        var output: StringBuffer
        val contentResolver = requireActivity().contentResolver
        val cursor : Cursor = contentResolver.query(CONTENT_URI, null, null, null, null)!!

        if(cursor.columnCount > 0){

            while(cursor.moveToNext()){
                val hasPhoneNumber =
                    cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)).toInt()
                val contactId = cursor.getString(cursor.getColumnIndex(_ID))
                val name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME))
                val phoneCursor = contentResolver.query(
                    PhoneCONTENT_URI, null,
                    "$Phone_CONTACT_ID = ?", arrayOf(contactId), null
                )
                if(hasPhoneNumber > 0)
                {
                    if (phoneCursor != null) {
                        while (phoneCursor.moveToNext()) {
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        }
                    }
                }
                val favContact = cursor.getString(cursor.getColumnIndex(IS_FAVORITE))
                val photo = cursor.getString(cursor.getColumnIndex(IMG))
                Log.i("Gg", "$name _ $phoneNumber _ $favContact _ $photo")
            }

        }
    }

}