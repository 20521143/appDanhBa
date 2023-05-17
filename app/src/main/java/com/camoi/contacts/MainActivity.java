package com.camoi.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter adapter;
    private ArrayList<Contact> arrayContact;
    private EditText etName;
    private EditText etNumber;
    private ListView lvContact;
    private Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayContact = new ArrayList<>();
        adapter = new ContactAdapter(this, R.layout.layout_itemlistview, arrayContact);
        /*lvContact.setAdapter(adapter);*/
    }
    public void setWidget(){
        etName = (EditText) findViewById(R.id.edt_name);
        etNumber = (EditText) findViewById(R.id.edt_number);
        btnAddContact = (Button) findViewById(R.id.btn_addcontact);
        lvContact = (ListView) findViewById(R.id.lv_contact);
    }
    public void addContact(View view){
        if(view.getId()==R.id.btn_addcontact){
            String name = etName.getText().toString().trim();
            String number = etNumber.getText().toString().trim();
            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(number)){
                Toast.makeText(this, "Vui lòng nhập Tên hoặc Số điện thoại", Toast.LENGTH_SHORT).show();
            }else{
                Contact contact = new Contact(name,number);
                arrayContact.add(contact);
            }
            adapter.notifyDataSetChanged();
        }
    }
}