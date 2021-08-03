package com.example.a2_contacts

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

//comment
val CONTENT_URI: Uri = ContactsContract.Contacts.CONTENT_URI
const val ID: String = ContactsContract.Contacts._ID
const val DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME

const val HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER
const val IS_FAVORITE = ContactsContract.Contacts.STARRED
const val IMG = ContactsContract.Contacts.PHOTO_URI

//comment
val PhoneCONTENT_URI: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
const val Phone_CONTACT_ID: String = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
const val NUMBER: String = ContactsContract.CommonDataKinds.Phone.NUMBER
//val NOTES : String = ContactsContract.CommonDataKinds.Note.NOTE

class ContactsAdapter(private val context: Context?, private val isFav: Boolean) :
    RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    private val contentResolver: ContentResolver = context!!.contentResolver
    private val cursor: Cursor = contentResolver.query(CONTENT_URI, null, null, null, null)!!
    private val cursorFav: Cursor = contentResolver.query(
        CONTENT_URI, null,
        "$IS_FAVORITE=?", arrayOf("1"), null
    )!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(position)
    }

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getItemCount(): Int {
        if (!isFav) {
            val cursor: Cursor = contentResolver.query(
                CONTENT_URI,
                null,
                ContactsContract.Contacts.NAME_RAW_CONTACT_ID,
                null,
                null
            )!!
            return cursor.count
        } else {
            val cursorFav: Cursor = contentResolver.query(
                CONTENT_URI, null,
                "$IS_FAVORITE=?", arrayOf("1"), null
            )!!
            return cursorFav.count
        }
    }

    inner class ContactViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private var nameTextView: TextView = view.findViewById<TextView>(R.id.text_name)
        private val numberTextView: TextView = view.findViewById<TextView>(R.id.text_number)
        private val photoImgView: ImageView = view.findViewById<ImageView>(R.id.img_contact_photo)
        private val favImgView: ImageView = view.findViewById<ImageView>(R.id.img_if_favorite)
        private val layoutForContact: LinearLayout = view.findViewById<LinearLayout>(R.id.layout_for_contact)

        @SuppressLint("Recycle")
        fun bind(position: Int) {
            var contactId = ""
            if (isFav) {
                var phoneNumber: String = " "
                //var phoneNotes: String = " "
                var temp: Int = 0
                cursorFav.moveToFirst()
                while (temp < position) {
                    cursorFav.moveToNext()
                    temp++
                }
                if (cursorFav.count > 0) {
                    Log.i("Gg", "oookk $temp and position = $position")
                    contactId = cursorFav.getString(cursorFav.getColumnIndex(ID))
                    val hasPhoneNumber =
                        cursorFav.getString(cursorFav.getColumnIndex(HAS_PHONE_NUMBER)).toInt()
                    val name = cursorFav.getString(cursorFav.getColumnIndex(DISPLAY_NAME))
                    val favContact = cursorFav.getString(cursorFav.getColumnIndex(IS_FAVORITE))
                    val photo = cursorFav.getString(cursorFav.getColumnIndex(IMG))
                    val phoneCursor = contentResolver.query(
                        PhoneCONTENT_URI, null,
                        "$Phone_CONTACT_ID = ?", arrayOf(contactId), null
                    )
                    if (phoneCursor != null) {
                        phoneCursor.moveToFirst()
                        phoneNumber = if (hasPhoneNumber > 0) {
                            phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER))
                        } else {
                            "NULL"
                        }
                    }
                    nameTextView.text = name
                    numberTextView.text = phoneNumber
                    if (favContact.toInt() == 0) {
                        favImgView.setImageResource(R.drawable.ic_baseline_star_border_24)
                    } else {
                        favImgView.setImageResource(R.drawable.ic_baseline_star_24)
                    }
                    if (photo != null) {
                        photoImgView.setImageURI(photo.toUri())
                    } else {
                        photoImgView.setImageResource(R.drawable.ic_baseline_perm_contact_calendar_24)
                    }

                } else {
                    Log.i("Gg", "Not funny")
                }
            } else {
                var phoneNumber: String = " "
                //var phoneNotes: String = " "
                var temp: Int = 0
                cursor.moveToFirst()
                while (temp < position) {
                    cursor.moveToNext()
                    temp++
                }
                if (cursor.count > 0) {
                    Log.i("Gg", "Funny")
                    contactId = cursor.getString(cursor.getColumnIndex(ID))
                    val hasPhoneNumber =
                        cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)).toInt()
                    val name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME))
                    val favContact = cursor.getString(cursor.getColumnIndex(IS_FAVORITE))
                    val photo = cursor.getString(cursor.getColumnIndex(IMG))
                    val phoneCursor = contentResolver.query(
                        PhoneCONTENT_URI, null,
                        "$Phone_CONTACT_ID = ?", arrayOf(contactId), null
                    )
                    if (phoneCursor != null) {
                        phoneCursor.moveToFirst()
                        phoneNumber = if (hasPhoneNumber > 0) {
                            phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER))
                        } else {
                            "NULL"
                        }
                    }
                    nameTextView.text = name
                    numberTextView.text = phoneNumber
                    if (favContact.toInt() == 0) {
                        favImgView.setImageResource(R.drawable.ic_baseline_star_border_24)
                    } else {
                        favImgView.setImageResource(R.drawable.ic_baseline_star_24)
                    }
                    if (photo != null) {
                        photoImgView.setImageURI(photo.toUri())
                    } else {
                        photoImgView.setImageResource(R.drawable.ic_baseline_perm_contact_calendar_24)
                    }

                } else {
                    Log.i("Gg", "Not funny")
                }
            }
            layoutForContact.setOnClickListener {
                val action = ContactsFragmentDirections.actionContactsFragmentToContactFragment2(contactID = contactId)
                view.findNavController().navigate(action)
            }
        }
    }
}