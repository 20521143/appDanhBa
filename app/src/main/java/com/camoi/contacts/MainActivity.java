package com.camoi.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.camoi.contacts.data.ContentProvider;
import com.camoi.contacts.data.Contract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ListView lvContact;

    // The data from the DroidTermsExample content provider
    private Cursor mData;

    private Button addNewContact;
    private static final int LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = (ListView) findViewById(R.id.lv_contact);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogConfirm();
            }
        });
        ArrayList<Contact> arrayList = new ArrayList<>();
        Contact contact = new Contact("ThuyChinh", "039876xxxx");
        Contact contact2 = new Contact("VanAnh", "039354xxxx");
        Contact contact3 = new Contact("TraMy", "096337xxxx");
        Contact contact4 = new Contact("ThanhCong", "098396xxxx");
        Contact contact5 = new Contact("ThuyChinhNguyen", "039876yyyy");
        Contact contact6 = new Contact("VanAnhNguyen", "039354yyyy");
        Contact contact7 = new Contact("TraMyDuong", "096337yyyy");
        Contact contact8 = new Contact("ThanhCongNguyen", "098396yyyy");

        arrayList.add(contact);
        arrayList.add(contact2);
        arrayList.add(contact3);
        arrayList.add(contact4);
        arrayList.add(contact5);
        arrayList.add(contact6);
        arrayList.add(contact7);
        arrayList.add(contact8);

//        for(Contact object : ContentResolveHelper.getEveryOne(contentResolver)) {
//            arrayList.add(object);
//
//        }

//        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.layout_itemlistview, arrayList);
//
//        lvContact.setAdapter(customAdapter);
        ArrayList<Contact> returnList = getContacts();

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, R.layout.layout_itemlistview,returnList);

        lvContact.setAdapter(customAdapter);

        addNewContact = (Button) findViewById(R.id.btn_addNewContact);
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);





            }
        });



    }

    @NonNull
    private ArrayList<Contact> getContacts() {
        Cursor cursor = getContentResolver().query(Contract.ContactEntry.CONTENT_URI,
                null,
                null,
                null,
                Contract.ContactEntry.COLUMN_NAME);
        ArrayList<Contact> returnList =new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
//                int contactID = cursor.getInt(0);
                String contactName = cursor.getString(1);
                String contactPhone = cursor.getString(2);
                Contact contact = new Contact(contactName, contactPhone);
                returnList.add(contact);
            } while(cursor.moveToNext());
        }
        else {
            //failure. do not add any thing to the list
        }
        cursor.close();
        return returnList;
    }


    public void showDialogConfirm() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_customdialog);
        Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel_addContact);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Call", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();


            }
        });
        dialog.show();
    }
}

