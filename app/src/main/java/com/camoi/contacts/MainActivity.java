package com.camoi.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
    private ListView lvContact;

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
        Contact contact = new Contact("ThuyChinh","039876xxxx");
        Contact contact2 = new Contact("VanAnh","039354xxxx");
        Contact contact3 = new Contact("TraMy","096337xxxx");
        Contact contact4 = new Contact("ThanhCong","098396xxxx");
        Contact contact5 = new Contact("ThuyChinhNguyen","039876yyyy");
        Contact contact6 = new Contact("VanAnhNguyen","039354yyyy");
        Contact contact7 = new Contact("TraMyDuong","096337yyyy");
        Contact contact8 = new Contact("ThanhCongNguyen","098396yyyy");

        arrayList.add(contact);
        arrayList.add(contact2);
        arrayList.add(contact3);
        arrayList.add(contact4);
        arrayList.add(contact5);
        arrayList.add(contact6);
        arrayList.add(contact7);
        arrayList.add(contact8);

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.layout_itemlistview,arrayList);
        lvContact.setAdapter(customAdapter);
    }
    public void showDialogConfirm(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_customdialog);
        Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
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