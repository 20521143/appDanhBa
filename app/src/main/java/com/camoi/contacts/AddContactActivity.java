package com.camoi.contacts;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.camoi.contacts.data.Contract;

public class AddContactActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);



    }

    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddTask(View view) {
        // Not yet implemented
        // TODO (6) Check if EditText is empty, if not retrieve input and store it in a ContentValues object
            String inputName = ((EditText) findViewById(R.id.et_name)).getText().toString();
            String inputPhone = ((EditText) findViewById(R.id.et_phone)).getText().toString();
            if(inputName.length() == 0 || inputPhone.length() == 0) {
                Toast.makeText(getBaseContext(), "insert failse", Toast.LENGTH_LONG).show();
                return;
            }

            //Create  new empty ContentValues object
        ContentValues contentValues = new ContentValues();
            //put the task description and selected
        contentValues.put(Contract.ContactEntry.COLUMN_NAME, inputName);
        contentValues.put(Contract.ContactEntry.COLUMN_PHONE, inputPhone);
        // TODO (7) Insert new task data via a ContentResolver
        Uri uri = getContentResolver().insert(Contract.ContactEntry.CONTENT_URI, contentValues);
        // TODO (8) Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if(uri != null) {
            Toast.makeText(getBaseContext(),"success: "+ uri.toString(), Toast.LENGTH_LONG).show();
        }
        finish();

    }
}
