package com.example.a2_contacts

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.FragmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return FragmentViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int) {
        holder.bind("Ok...")
    }

    override fun getItemCount(): Int {
        return 0
    }

    class FragmentViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        var nameView: TextView = view.findViewById<TextView>(R.id.text_name)
        val numberView: TextView = view.findViewById<TextView>(R.id.text_number)
        val imgView: ImageView = view.findViewById(R.id.img_contact_photo)
        val favImgView: ImageView = view.findViewById(R.id.img_if_favorite)

        fun bind(item: String)
        {
            //bind
        }

        @SuppressLint("Recycle")
        private fun getContacts(){
            var phoneNumber : String
            var CONTENT_URI : Uri = ContactsContract.Contacts.CONTENT_URI
            var _ID : String = ContactsContract.Contacts._ID
            var DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME
            var HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER

            var PhoneCONTENT_URI : Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            var Phone_CONTACT_ID : String = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
            var NUMBER : String = ContactsContract.CommonDataKinds.Phone.NUMBER

            var output: StringBuffer
            val context = view.findViewById<RecyclerView>(R.id.recyclerView).context
            val contentResolver: ContentResolver = context.contentResolver

            val cursor : Cursor = contentResolver.query(CONTENT_URI, null, null, null, null)!!

            if(cursor.columnCount > 0){

                while(cursor.moveToNext()){
                    val contact_id = cursor.getString(cursor.getColumnIndex(_ID))
                    val name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME))
                    val hasPhoneNumber =
                        cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)).toInt()
                    if(hasPhoneNumber > 0){
                        Log.i("lol...", name)
                    }
                }

            }
        }
    }
}