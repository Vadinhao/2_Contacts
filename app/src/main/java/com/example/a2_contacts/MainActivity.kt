package com.example.a2_contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        ContentResolver resolver = getContentResolver();
        mData = resolver.query(TVContract.Actors.CONTENT_URI, null, null, null, null);

        int wordCol = cursor.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD);
        int definitionCol = cursor.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION);
        while (cursor.moveToNext()) {
        String word = cursor.getString(wordCol);
        String definition = cursor.getString(definitionCol);
        Log.v(“Cursor Example”, word + “ - “ + definition);
        */

    }
}